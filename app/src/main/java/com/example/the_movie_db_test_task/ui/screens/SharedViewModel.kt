package com.example.the_movie_db_test_task.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the_movie_db_test_task.data.api.discaver.Movie
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
    var movies = mutableStateOf<MutableList<Movie>>(mutableListOf())
    var endReached = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf(false)
    var isEmpty = mutableStateOf(false)
    var moviesDB = mutableStateOf<MutableList<Movie>>(mutableListOf())
    init {
        getMovies()
    }

    private fun error() {
        isLoading.value = false
        isError.value = true
    }

    fun getMovies() {
        viewModelScope.launch {
            if (isLoading.value == false){
                isLoading.value = true
                when (val result = repository.getMovies(curPage)) {
                    is Resource.Success -> {
                        movies.value.addAll(result.data!!.results as MutableList<Movie>)
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
    }

    fun getFavorites() {
        viewModelScope.launch {
            moviesDB.value = repository.getMoviesFromDB() as MutableList<Movie>
            isEmpty.value = repository.getMoviesFromDB().isEmpty()
        }
    }

    fun postFavorite(movieItem: Movie) {
        viewModelScope.launch {
            when (repository.postFavorites(
                movieItem.id,
                isAddToFavorite = moviesDB.value.any { it.id != movieItem.id })) {
                is Resource.Success -> {
                    if (moviesDB.value.any { it.id == movieItem.id }) {
                        repository.deleteDeviseFromDB(movieItem)
                        if (repository.getMoviesFromDB().isEmpty()) {
                            isEmpty.value = true
                        }
                    } else {
                        repository.addMovie(movieItem)
                    }
                    moviesDB.value = repository.getMoviesFromDB() as MutableList<Movie>
                }
                is Resource.Error -> {
                    error()
                }
            }
        }
    }
}