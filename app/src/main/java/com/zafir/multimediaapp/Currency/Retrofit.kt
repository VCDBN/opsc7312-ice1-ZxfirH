package com.zafir.multimediaapp.Currency

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {
    const val BASE_URL = "https://api.api-ninjas.com/"

    fun createRetrofitService(): ApiInter {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ApiInter::class.java)
    }
}