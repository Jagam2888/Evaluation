package com.jagad.evaluation.model.response

import com.jagad.evaluation.model.mostPopular.Result

data class MostPopularResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)