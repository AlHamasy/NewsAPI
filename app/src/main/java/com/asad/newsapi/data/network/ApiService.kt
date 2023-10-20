package com.asad.newsapi.data.network

import com.asad.newsapi.data.network.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("apiKey") apiKey : String,
        @Query("country") country : String = "us",
        @Query("page") page : Int) : NewsResponse

}