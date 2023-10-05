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
import com.example.the_movie_db_test_task.ui.items.MovieCardItem
import com.example.the_movie_db_test_task.ui.screens.favorites.FavoruritesViewModel
import com.example.the_movie_db_test_task.ui.screens.favorites.SharedViewModel
import com.example.the_movie_db_test_task.utils.Constants
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FavouritesScreen(viewModel: SharedViewModel = hiltViewModel()) {
    val movieDB by remember { viewModel.movieDB }
    val isLoading by remember { viewModel.isLoading }
    val isError by remember { viewModel.isError }
    val scroll = rememberLazyListState()
    val isEmpty by remember { viewModel.isEmpty }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

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
                Button(
                    onClick = {
                        viewModel.getFavorites()
                    }) {
                    Text(text = "Update")
                }
            }
            isEmpty -> {
                Text(text = "EMPTY")
                Button(
                    onClick = {
                        viewModel.getFavorites()
                    }) {
                    Text(text = "Update")
                }
            }
            else -> {
                SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getFavorites() }) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), state = scroll
                    ) {
                        items(movieDB) { item ->
                            MovieCardItem(
                                movie = item,
                                textFirstBat = Constants.remove,
                                onClickSecondBut = {},
                                onClickFirstBut = { viewModel.postFavorite(item) })
                        }
                    }
                }
            }
        }
    }
}