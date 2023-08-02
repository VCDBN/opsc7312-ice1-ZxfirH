package com.zafir.multimediaapp.Weather

import com.zafir.multimediaapp.Weather.Imperial
import com.zafir.multimediaapp.Weather.Metric

data class Temperature(
    val Imperial: Imperial,
    val Metric: Metric
)