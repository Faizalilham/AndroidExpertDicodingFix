package code.faizal.rakaminchallenge.core.data

import android.util.Log
import code.faizal.rakaminchallenge.core.data.datasource.local.LocalDataSource
import code.faizal.rakaminchallenge.core.data.datasource.remote.RemoteDataSource
import code.faizal.rakaminchallenge.core.data.datasource.remote.network.ApiResponse
import code.faizal.rakaminchallenge.core.data.datasource.remote.response.NewsResponse
import code.faizal.rakaminchallenge.core.domain.model.News
import code.faizal.rakaminchallenge.core.domain.repository.INewsRepository
import code.faizal.rakaminchallenge.core.utils.AppExecutors
import code.faizal.rakaminchallenge.core.utils.Util.mapFromDomainToEntity
import code.faizal.rakaminchallenge.core.utils.Util.mapFromEntityToDomain
import code.faizal.rakaminchallenge.core.utils.Util.mapFromResponseToEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataSource @Inject constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    private val appExecutors : AppExecutors
): INewsRepository {
    override fun getAllHeadLine(): Flow<Resource<List<News>>> {
       return remoteDataSource.getAllHeadLine()
    }

    override fun getAllNews(): Flow<Resource<List<News>>> {
        return object : NetworkResourceBound<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map {
                    mapFromEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> {
                return remoteDataSource.getAllNews()
            }

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val listNews = mapFromResponseToEntities(data)
                localDataSource.insertNews(listNews)
            }

        }.asFlow()
    }

    override fun favorites(): Flow<List<News>> {
        return localDataSource.getAllFavorite().map {
            mapFromEntityToDomain(it)
        }
    }

    override fun setFavorite(news: News, isFavorite: Boolean) {
        val newsEntity = mapFromDomainToEntity(news,isFavorite)

        Log.d("HAKARI","${newsEntity.isFavorite}")

        appExecutors.diskIO().execute{

            localDataSource.updateNewsFavorite(newsEntity, isFavorite)
        }
    }

}