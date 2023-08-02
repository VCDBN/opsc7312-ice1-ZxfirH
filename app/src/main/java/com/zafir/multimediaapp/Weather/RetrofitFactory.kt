package com.zafir.multimediaapp.Weather

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    const val BASE_URL = "http://dataservice.accuweather.com/"

    fun makeRetrofitService(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }
}