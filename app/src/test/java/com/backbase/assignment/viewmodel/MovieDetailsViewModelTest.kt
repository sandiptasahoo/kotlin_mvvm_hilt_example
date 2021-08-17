package com.backbase.assignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.data.model.Genre
import com.backbase.assignment.data.model.MovieDetails

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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var movieDetailsStub: MovieDetails

    @Mock
    private lateinit var viewModel: MovieDetailsViewModel

    @Mock
    private lateinit var repository: Repository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = Mockito.spy(
            MovieDetailsViewModel(
                repository
            )
        )
        movieDetailsStub = MovieDetails(
            posterPath = "/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
            overview = "test",
            releaseDate = "2020-12-16",
            title = "Luca",
            runtime = 95,
            genres = listOf(
                Genre(id = 16, name = "Animation"),
                Genre(id = 10751, name = "Family"),
            ),
            voteAverage = 8.9
        )
    }

    @Test
    fun `given getMovieDetails() is called and returns the MovieDetails data`() {
        testCoroutineRule.runBlockingTest {
            whenever(repository.getMovieDetails(581644)).thenReturn(movieDetailsStub)

            viewModel.getMovieDetais(581644)
            Assert.assertTrue(viewModel.movieDetailsPage.value is MovieDetails)
        }
    }
}