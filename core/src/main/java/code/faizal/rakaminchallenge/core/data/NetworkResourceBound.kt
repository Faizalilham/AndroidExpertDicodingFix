package code.faizal.rakaminchallenge.core.data

import code.faizal.rakaminchallenge.core.data.datasource.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@Suppress("EmptyMethod")
abstract class NetworkResourceBound<ResultType,RequestType> {

    private val result : Flow<Resource<ResultType>> = flow{
        emit(Resource.Loading())
        val db = loadFromDB().first()
        if(shouldFetch(db)){
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.message))
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }

            }
        }else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }

    }
    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow() = result


}