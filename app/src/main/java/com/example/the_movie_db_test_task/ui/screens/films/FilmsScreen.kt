package com.example.the_movie_db_test_task.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the_movie_db_test_task.data.model.discaver.Movie
import com.example.the_movie_db_test_task.ui.items.DataItem
import com.example.the_movie_db_test_task.ui.items.MovieCardItem
import com.example.the_movie_db_test_task.ui.screens.favorites.SharedViewModel
import com.example.the_movie_db_test_task.utils.Constants
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FilmsScreen(viewModel: SharedViewModel = hiltViewModel()) {
    val outputFormat: DateFormat = SimpleDateFormat("MMM yyyy")
    var caunter = 1
    val movie by remember { viewModel.movie }
    val endReached by remember { viewModel.endReached }
    val isLoading by remember { viewModel.isLoading }
    val isError by remember { viewModel.isError }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    var date: Date? = null
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    color = Color.Blue,

                    )
            }
            isError -> {
                Text(text = "ERROR")
            }
            else -> {
                SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getMovies() }) {
                    LazyColumn(contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(movie) { item ->
                            if (item.id == movie.last().id) {
                                viewModel.getMovies()
                            }
                            if (item.releaseDate.isNotEmpty()) {
                                if (date == null) {
                                    date = convertData(item.releaseDate)
                                    DataItem(data = outputFormat.format(date))
                                } else {
                                    if (date!! > convertData(item.releaseDate)) {
                                        date = convertData(item.releaseDate)
                                        DataItem(data = outputFormat.format(date))
                                    }
                                }
                            }
                            MovieCardItem(
                                movie = item,
                                onClickSecondBut = {},
                                textFirstBat = if (viewModel.movieDB.value.any { it.id == item.id }) {
                                    Constants.unlike
                                } else {
                                    Constants.like
                                },
                                onClickFirstBut = { viewModel.postFavorite(item) })
                        }
                    }
                }
            }
        }
    }
}

fun convertData(inputData: String): Date? {
    return when {
        inputData.isNotEmpty() -> {
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat: DateFormat = SimpleDateFormat("MMM yyyy")
            val date: Date? = inputFormat.parse(inputData)
            val stringData = date?.let { outputFormat.format(it) }
            stringData?.let { outputFormat.parse(it) }
        }
        else -> {
            null
        }
    }
}
