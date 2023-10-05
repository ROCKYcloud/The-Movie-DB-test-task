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
class FavoruritesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var curPage = 1

    @SuppressLint("MutableCollectionMutableState")
    var movie = mutableStateOf<MutableList<Movie>>(mutableListOf())
    var endReached = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var isEmpty = mutableStateOf(false)
    var isError = mutableStateOf(false)

    init {
        getFavorites()
    }

    private fun loading() {
        isLoading.value = true
        isError.value = false
    }

    private suspend fun error() {
        isLoading.value = false
        isError.value = true
        movie.value = repository.getMoviesFromDB() as MutableList<Movie>
    }

    fun getFavorites() {
        viewModelScope.launch {
            isEmpty.value = repository.getMoviesFromDB().isEmpty()
            movie.value = repository.getMoviesFromDB() as MutableList<Movie>
        }
    }

    fun postFravorite(movieItem: Movie, isAddToFavorite: Boolean) {
        viewModelScope.launch {
            when (repository.postFavorites(movieItem.id, isAddToFavorite)) {
                is Resource.Success -> {
                    repository.deleteDeviseFromDB(movieItem)
                    movie.value = repository.getMoviesFromDB() as MutableList<Movie>
                }
                is Resource.Error -> {
                    error()
                }
            }
        }
    }

}