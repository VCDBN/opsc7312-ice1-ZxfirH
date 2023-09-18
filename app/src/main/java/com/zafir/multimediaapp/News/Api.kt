package com.zafir.multimediaapp.News

import com.zafir.multimediaapp.Currency.CurrenyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v2/everything?from=2023-08-14&to=2023-08-14&sortBy=popularity&apiKey=bfb04ff6b3d540c1bb412e01cf06d3dc")
    suspend fun getNews(@Query("q") source: String?): Response<NewsData>
}