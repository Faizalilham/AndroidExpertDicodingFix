package code.faizal.rakaminchallenge.core.data.datasource.remote

import android.util.Log
import code.faizal.rakaminchallenge.core.data.Resource
import code.faizal.rakaminchallenge.core.data.datasource.remote.network.ApiResponse
import code.faizal.rakaminchallenge.core.data.datasource.remote.network.ApiService
import code.faizal.rakaminchallenge.core.data.datasource.remote.response.NewsResponse
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.utils.Util
import code.faizal.rakaminchallenge.core.utils.Util.mapFromEntityToDomain
import code.faizal.rakaminchallenge.core.utils.Util.mapFromResponseToEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    fun getAllHeadLine(): Flow<Resource<List<News>>> = flow {
        try {

            val response = apiService.getListNews(Util.getCountry(),"f36b8bb468b841ffb584a3dc760fc407")
            val data = response.articles
            Log.d("UHUY 1","$data")
            if(data.isNotEmpty()){
                val entity = mapFromResponseToEntities(data)
                emit(Resource.Success(mapFromEntityToDomain(entity)))
            }else{
                emit(Resource.Loading())
            }

        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))

        }
    }.flowOn(Dispatchers.IO)

    fun getAllNews(): Flow<ApiResponse<List<NewsResponse>>> = flow {
        try {

            val response = apiService.getSearchNews("a","f36b8bb468b841ffb584a3dc760fc407")
            val data = response.articles
            Log.d("UHUY 2","$data")
            if(data.isNotEmpty()){
                emit(ApiResponse.Success(data))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (e : Exception){
            emit(ApiResponse.Error(e.message.toString()))

        }
    }.flowOn(Dispatchers.IO)
}