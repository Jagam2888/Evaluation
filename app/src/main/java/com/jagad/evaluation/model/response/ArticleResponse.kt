package com.jagad.evaluation.model.response

import com.jagad.evaluation.model.searchResponse.Response

data class ArticleResponse(
    val copyright: String,
    val response: Response,
    val status: String
)