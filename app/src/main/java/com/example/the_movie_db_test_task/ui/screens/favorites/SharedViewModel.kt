package com.example.the_movie_db_test_task.ui.screens.favorites

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the_movie_db_test_task.data.model.discaver.Movie
import com.example.the_movie_db_test_task.data.repository.Repository
import com.example.the_movie_db_test_task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var curPage = 1
    @SuppressLint("MutableCollectionMutableState")
    var movie = mutableStateOf<MutableList<Movie>>(mutableListOf())
    var endReached = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf(false)
    var isEmpty = mutableStateOf(false)

    var movieDB = mutableStateOf<MutableList<Movie>>(mutableListOf())

    init {
        getMovies()
    }

    private fun loading() {
        isLoading.value = true
        isError.value = false
    }

    private fun error() {
        isLoading.value = false
        isError.value = true
    }

    fun getMovies() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getMovies(curPage)) {
                is Resource.Success -> {
                    movie.value.addAll(result.data!!.results as MutableList<Movie>)
                    isLoading.value = false
                    isError.value = false
                    curPage++
                }
                is Resource.Error -> {
                    error()
                }
            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
//            if(repository.getMoviesFromDB().isEmpty()){
//                when(     val result = repository.getFavorites()){
//                    is Resource.Loading -> {
//                        loading()
//                    }
//                    is Resource.Success -> {
//                        re
//                        movieDB.value = repository.getMoviesFromDB() as MutableList<Movie>
//                    }
//                    is Resource.Error -> {
//                        error()
//                    }
//                }
//            }
//
            movieDB.value = repository.getMoviesFromDB() as MutableList<Movie>
            isEmpty.value = repository.getMoviesFromDB().isEmpty()
        }
    }

    fun postFavorite(movieItem: Movie) {
        viewModelScope.launch {
            when (repository.postFavorites(
                movieItem.id,
                isAddToFavorite = movieDB.value.any { it.id != movieItem.id })) {
                is Resource.Success -> {
                    if (movieDB.value.any { it.id == movieItem.id }) {
                        repository.deleteDeviseFromDB(movieItem)
                        if (repository.getMoviesFromDB().isEmpty()){
                            isEmpty.value = true
                        }
                    } else {
                        repository.addMovie(movieItem)
                    }
                    movieDB.value = repository.getMoviesFromDB() as MutableList<Movie>
                }
                is Resource.Error -> {
                    error()
                }
            }
        }
    }
}