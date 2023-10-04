package com.example.the_movie_db_test_task.ui.screens

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
class FilmsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var curPage = 1

    @SuppressLint("MutableCollectionMutableState")
    var movie = mutableStateOf<MutableList<Movie>>(mutableListOf())
    var endReached = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf(false)

    init {
        getMovies()
    }

    private fun loading() {
        isLoading.value = true
        isError.value = false
    }

    private suspend fun error() {
        isLoading.value = false
        isError.value = true
     //   devices.value = repository.getDevisesFromDB() as MutableList<Device>
    }
    fun getMovies() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getMovies(curPage)) {
                is Resource.Loading -> {
                    loading()
                }
                is Resource.Success -> {
                    isLoading.value = false
                    isError.value = false
                    curPage++
                    movie.value += result.data!!.results as MutableList<Movie>
                }
                is Resource.Error -> {
                    error()
                }
            }
        }
    }

    fun postFaorite(movieId:Long,isAddToFavorite:Boolean){
        viewModelScope.launch {
            // isLoading.value = true
            when (val result = repository.postFavorites(movieId, isAddToFavorite = isAddToFavorite)) {
                is Resource.Loading -> {
                    //   loading()
                    Log.d("result","Secsses")
                }
                is Resource.Success -> {
                    Log.d("result","Secsses")
                    getMovies()
                }
                is Resource.Error -> {
                    //   error()
                    Log.d("result","Error")
                }
            }
        }
    }

}

