package com.backbase.assignment.viewmodel

import androidx.lifecycle.*
import com.backbase.assignment.data.model.MovieDetails
import com.backbase.assignment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository : Repository) : ViewModel() {

    val movieDetailsPage: MutableLiveData<MovieDetails> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val apiError: MutableLiveData<String> = MutableLiveData()

    fun getMovieDetais(movieId : Int){
        loading.value = true
        viewModelScope.launch {
            try {
                val popularMovieList = repository.getMovieDetails(movieId)
                movieDetailsPage.postValue(popularMovieList)
                loading.value = false
            }catch (e: Exception){
                loading.value = false
                apiError.postValue("Something Went Wrong")
            }
        }
    }
}