package com.zafir.multimediaapp.News

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)