package com.example.the_movie_db_test_task.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
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
import com.example.the_movie_db_test_task.ui.items.MovieCardItem
import com.example.the_movie_db_test_task.ui.items.UpdateUI
import com.example.the_movie_db_test_task.utils.Constants
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FavouritesScreen(viewModel: SharedViewModel = hiltViewModel()) {
    val moviesDB by remember { viewModel.moviesDB }
    val isLoading by remember { viewModel.isLoading }
    val isError by remember { viewModel.isError }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading ->
                CircularProgressIndicator(color = Color.Blue)

            isError ->
                UpdateUI(text = "Error") { viewModel.getFavorites() }

            moviesDB.isEmpty() && isLoading.not() && isError.not() ->
                UpdateUI(text = "Empty") { viewModel.getFavorites() }

            else ->
                MovieListFromDB(isLoading = isLoading, moviesDB = moviesDB, viewModel = viewModel)

        }
    }
}

@Composable
fun MovieListFromDB(isLoading: Boolean, moviesDB: List<Movie>, viewModel: SharedViewModel) {
    val scroll = rememberLazyListState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getFavorites() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), state = scroll
        ) {
            items(
                moviesDB,
                Movie::id
            ) { item ->
                MovieCardItem(
                    movie = item,
                    textFirstBat = Constants.remove,
                    onClickSecondBut = {},
                    onClickFirstBut = { viewModel.postFavorite(item) })
            }
        }
    }
}

