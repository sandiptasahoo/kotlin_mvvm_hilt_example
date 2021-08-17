package com.backbase.assignment.data.remote

import com.backbase.assignment.data.remote.model.MovieDetailsDTO
import com.backbase.assignment.data.remote.model.MoviePageDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): MoviePageDTO

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): MoviePageDTO

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): MovieDetailsDTO
}