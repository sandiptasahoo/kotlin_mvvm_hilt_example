package com.backbase.assignment.repository

import com.backbase.assignment.data.model.MovieDetails
import com.backbase.assignment.data.model.MoviePage

interface Repository {
    suspend fun getNowPlayingMovieList() : MoviePage
    suspend fun getMovieDetails(movieId : Int) : MovieDetails
}