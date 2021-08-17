package com.backbase.assignment.data.model

data class MoviePage(
    val page: Int?,
    val results: List<Movie>?,
    val totalPages: Int?,
    val totalResults: Int?
)