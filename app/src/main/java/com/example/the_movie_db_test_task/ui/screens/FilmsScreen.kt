package com.example.the_movie_db_test_task.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.the_movie_db_test_task.ui.items.MovieCardItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FilmsScreen(viewModel: FilmsViewModel = hiltViewModel()) {
    val movie by remember { viewModel.movie }
    val endReached by remember { viewModel.endReached }
    val isLoading by remember { viewModel.isLoading }
    val isError by remember { viewModel.isError }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    color = Color.Blue,

                )
            }
            isError -> {
                Text(text = "ERROR")}
            else -> {
                SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getMovies() }) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        val itemCount = 0
                        items(movie) { item ->
                            MovieCardItem(
                                movie = item,
                                onClickSecondBut = {},
                                onClickFirstBut = { viewModel.postFaorite(item.id, true) })
                        }
                    }
                }
            }
        }
    }
}

fun convertData(inputData: String): String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat: DateFormat = SimpleDateFormat("MMM yyyy")

    val date: Date = inputFormat.parse(inputData)
    return outputFormat.format(date)
}
