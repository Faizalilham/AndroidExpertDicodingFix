package code.faizal.rakaminchallenge.core.data.datasource.remote.network

import code.faizal.rakaminchallenge.core.data.datasource.remote.response.ListNewsResponse
import code.faizal.rakaminchallenge.core.data.datasource.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines/")
    suspend fun getListNews(@Query("country")country:String,
                    @Query("apiKey")apiKey:String): ListNewsResponse<NewsResponse>

    @GET("everything/")
    suspend fun getSearchNews(@Query("q")q:String,
                      @Query("apiKey")apiKey:String): ListNewsResponse<NewsResponse>
}