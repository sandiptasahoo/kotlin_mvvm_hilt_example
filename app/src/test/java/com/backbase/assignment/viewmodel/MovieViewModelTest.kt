package com.backbase.assignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.data.model.Movie
import com.backbase.assignment.data.model.MoviePage
import com.backbase.assignment.data.remote.MovieServices
import com.backbase.assignment.repository.Repository
import com.backbase.assignment.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var moviePageStub: MoviePage

    @Mock
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieServices: MovieServices

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(
            MovieViewModel(
                repository, movieServices
            )
        )
        moviePageStub = MoviePage(
            page = 1,
            results = listOf(
                Movie(
                    id = 581644,
                    backdropPath = "/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
                    overview = "test",
                    posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                    releaseDate = "2020-12-16",
                    title = "Luca",
                    voteAverage = 7.1,
                ),
                Movie(
                    id = 481644,
                    backdropPath = "/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
                    overview = "overview test",
                    posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                    releaseDate = "2020-12-19",
                    title = "Hunted",
                    voteAverage = 8.1,
                )
            ),
            totalResults = 8000,
            totalPages = 50
        )
    }

    @Test
    fun `given getNowPlayingMoviePage() is called and returns the MovePage data`() {
        testCoroutineRule.runBlockingTest {
            whenever(repository.getNowPlayingMovieList()).thenReturn(moviePageStub)

            viewModel.getNowPlayingMoviePage()
            Assert.assertTrue(viewModel.nowPlayingMovies.value is MoviePage)
        }
    }
}

