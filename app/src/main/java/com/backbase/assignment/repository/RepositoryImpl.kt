package com.backbase.assignment.repository

import com.backbase.assignment.data.model.MovieDetails
import com.backbase.assignment.data.model.MoviePage
import com.backbase.assignment.data.remote.MovieServices
import com.backbase.assignment.data.remote.mapper.mapToMovieDetailsModel
import com.backbase.assignment.data.remote.mapper.mapToMoviePageModel


class RepositoryImpl constructor(private val movieServices: MovieServices) : Repository {

    override suspend fun getNowPlayingMovieList(): MoviePage {
        return movieServices.getNowPlayingMovies(
            language = "en-US",
            apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
        ).mapToMoviePageModel()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieServices.getMovieDetail(
            movieId = movieId,
            language = "en-US",
            apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
        ).mapToMovieDetailsModel()
    }

}