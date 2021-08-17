package com.backbase.assignment.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.backbase.assignment.data.model.Movie
import com.backbase.assignment.data.model.MoviePage
import com.backbase.assignment.data.remote.MovieServices
import com.backbase.assignment.repository.MoviePageSource
import com.backbase.assignment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository : Repository, private val movieServices: MovieServices) : ViewModel() {

    val nowPlayingMovies: MutableLiveData<MoviePage> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val apiError: MutableLiveData<String> = MutableLiveData()


    //Get list of popular movies
    fun getPopularMoviePage() : Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePageSource(movieServices)
    }.flow.cachedIn(viewModelScope)

    //Get list of now playing movies
    fun getNowPlayingMoviePage(){
        loading.value = true
        viewModelScope.launch() {
            try {
                val nowPlayingMovieList = repository.getNowPlayingMovieList()
                nowPlayingMovies.postValue(nowPlayingMovieList)
                loading.value = false
            }catch (error: Exception){
                loading.value = false
                apiError.postValue("Something Went Wrong")
            }

        }
    }
}