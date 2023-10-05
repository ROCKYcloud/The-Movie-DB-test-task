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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the_movie_db_test_task.data.api.discaver.Movie
import com.example.the_movie_db_test_task.ui.items.DataItem
import com.example.the_movie_db_test_task.ui.items.MovieCardItem
import com.example.the_movie_db_test_task.utils.Constants
import com.example.the_movie_db_test_task.utils.convertData
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FilmsScreen(viewModel: SharedViewModel = hiltViewModel()) {
    val movies by remember { viewModel.movies }
    val moviesDB by remember { viewModel.moviesDB }
    val isLoading by remember { viewModel.isLoading }
    val isError by remember { viewModel.isError }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isError -> Text(text = Constants.error)
            else -> {
                if (isLoading && movies.isEmpty()) { CircularProgressIndicator(color = Color.Blue)
                } else {
                        MovieList(
                            movies = movies,
                            isLoading = isLoading,
                            moviesDB = moviesDB,
                            viewModel = viewModel
                        )
                }
            }
        }
    }
}

@Composable
fun MovieList(
    movies: List<Movie>,
    isLoading: Boolean,
    moviesDB: List<Movie>,
    viewModel: SharedViewModel
) {
    var date: Date? = remember { null }
    val outputFormat: DateFormat = SimpleDateFormat("MMM yyyy")
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getMovies() }) {

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                movies,
                Movie::id
            ) { item ->
                if (item.id == movies.last().id) {
                    viewModel.getMovies()
                }
                if (item.releaseDate.isNotEmpty()) {
                    date?.let {
                        if (it > convertData(item.releaseDate)) {
                            date = convertData(item.releaseDate)
                            DataItem(data = outputFormat.format(date))
                        }
                    }?.run {
                        date = convertData(item.releaseDate)
                        DataItem(data = outputFormat.format(date))
                    }
                }
                MovieCardItem(
                    movie = item,
                    onClickSecondBut = {},
                    textFirstBat = if (moviesDB.any { it.id == item.id }) {
                        Constants.unlike
                    } else {
                        Constants.like
                    },
                    onClickFirstBut = { viewModel.postFavorite(item) })
            }
            item {
                if (isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
        }
    }
}




