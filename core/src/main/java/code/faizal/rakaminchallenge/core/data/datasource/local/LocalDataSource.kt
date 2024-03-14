package code.faizal.rakaminchallenge.core.data.datasource.local

import android.util.Log
import code.faizal.rakaminchallenge.core.data.datasource.local.entity.NewsEntity
import code.faizal.rakaminchallenge.core.data.datasource.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor( private val newsDao : NewsDao) {

    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.news()

    fun getAllFavorite(): Flow<List<NewsEntity>> = newsDao.favorites()

    suspend fun insertNews(news : List<NewsEntity>) = newsDao.insertNews(news)

    fun updateNewsFavorite(newsEntity : NewsEntity, isFavorite : Boolean){

        newsEntity.isFavorite = isFavorite
        Log.d("HILDAN KUNYUK","$newsEntity")
        newsDao.updateNews(newsEntity)
    }
}