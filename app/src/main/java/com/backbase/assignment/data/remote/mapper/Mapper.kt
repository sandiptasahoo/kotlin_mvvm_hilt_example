package com.backbase.assignment.data.remote.mapper

import com.backbase.assignment.data.model.Genre
import com.backbase.assignment.data.model.Movie
import com.backbase.assignment.data.model.MovieDetails
import com.backbase.assignment.data.model.MoviePage
import com.backbase.assignment.data.remote.model.GenreDTO
import com.backbase.assignment.data.remote.model.MovieDTO
import com.backbase.assignment.data.remote.model.MovieDetailsDTO
import com.backbase.assignment.data.remote.model.MoviePageDTO

fun MoviePageDTO.mapToMoviePageModel(): MoviePage {
    return MoviePage(
        page = page,
        results = results?.map { it.mapToMovieModel() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

private fun MovieDTO.mapToMovieModel(): Movie {
    return Movie(
        id = id,
        posterPath = posterPath,
        title = title,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}


fun MovieDetailsDTO.mapToMovieDetailsModel(): MovieDetails {
    return MovieDetails(
        posterPath = poster_path,
        genres = genres?.map { it.mapToGenreModel() },
        overview = overview,
        releaseDate = release_date,
        title = title,
        runtime = runtime,
        voteAverage = vote_average,
    )
}

private fun GenreDTO.mapToGenreModel(): Genre {
    return Genre(
        id = id,
        name = name
    )
}