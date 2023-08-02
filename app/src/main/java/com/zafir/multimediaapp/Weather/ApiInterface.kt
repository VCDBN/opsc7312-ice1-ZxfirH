package com.zafir.multimediaapp.Weather

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("currentconditions/v1/305605?apikey=d4cLebhw8Kn1QuBWwEeQGgy06rJYTdGz")
    suspend fun getData(): Response<List<MyWeatherDataItem>>
}