package com.zafir.multimediaapp.News

data class NewsData(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)