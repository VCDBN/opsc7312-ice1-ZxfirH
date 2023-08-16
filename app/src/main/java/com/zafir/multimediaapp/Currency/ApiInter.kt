package com.zafir.multimediaapp.Currency

import retrofit2.Response
import retrofit2.http.GET

interface ApiInter {
    @GET("v1/convertcurrency?want=EUR&have=USD&amount=5000")
    suspend fun getCurrency(): Response<CurrenyData>
}