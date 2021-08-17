package com.backbase.assignment.data.model

import com.backbase.assignment.data.remote.model.GenreDTO

data class MovieDetails (
    val posterPath : String?,
    val genres: List<Genre>?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?,
    val runtime: Int?,
    val voteAverage: Double?
)