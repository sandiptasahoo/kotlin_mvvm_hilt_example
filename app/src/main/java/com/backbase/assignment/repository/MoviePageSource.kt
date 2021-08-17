package com.backbase.assignment.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.backbase.assignment.data.model.Movie
import com.backbase.assignment.data.remote.MovieServices
import com.backbase.assignment.data.remote.mapper.mapToMoviePageModel
import retrofit2.HttpException
import java.io.IOException

class MoviePageSource constructor(private val movieServices: MovieServices) : PagingSource<Int, Movie>(){

    private val DEFAULT_PAGE_INDEX= 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = movieServices.getPopularMovies(
                language = "en-US",
                page = pageIndex,
                apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
            ).mapToMoviePageModel()

            val movies = response.results!!
            val nextKey = if (movies.isEmpty()) null else pageIndex + 1

            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == DEFAULT_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}