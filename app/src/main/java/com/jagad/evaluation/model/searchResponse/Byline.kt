package com.jagad.evaluation.model.searchResponse

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)