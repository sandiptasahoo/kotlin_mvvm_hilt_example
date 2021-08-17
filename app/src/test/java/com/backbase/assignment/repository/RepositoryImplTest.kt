package com.backbase.assignment.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.data.remote.MovieServices
import com.backbase.assignment.data.remote.model.MovieDetailsDTO
import com.backbase.assignment.data.remote.model.MoviePageDTO
import com.backbase.assignment.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var moviePageDTOStub: MoviePageDTO

    @Mock
    private lateinit var movieDetailsDTOStub: MovieDetailsDTO

    @Mock
    private lateinit var movieServices: MovieServices

    private lateinit var repository: Repository

    @Before
    fun setup() {
        repository = spy(RepositoryImpl(movieServices))
    }

    @Test
    fun `given getNowPlayingMovieList() is called and returns the MoviePage data`() {
        testCoroutineRule.runBlockingTest {
            doReturn(moviePageDTOStub).whenever(movieServices).getNowPlayingMovies(
                language = "en-US",
                apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
            )
            repository.getNowPlayingMovieList()
            verify(repository).getNowPlayingMovieList()
        }
    }

    @Test
    fun `given getMovieDetails() is called and returns the Movie data`() {
        testCoroutineRule.runBlockingTest {
            doReturn(movieDetailsDTOStub).whenever(movieServices).getMovieDetail(
                movieId = 123, language = "en-US",
                apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
            )
            repository.getMovieDetails(123)
            verify(repository).getMovieDetails(123)
        }
    }
}