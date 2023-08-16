package com.zafir.multimediaapp.News

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retro {
    const val BASE_URL = "https://newsapi.org/"

    fun createRetro(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Api::class.java)
    }
}