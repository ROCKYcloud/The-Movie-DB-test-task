package com.example.the_movie_db_test_task.ui.items

import androidx.compose.runtime.Composable
import com.example.the_movie_db_test_task.ui.screens.FavouritesScreen
import com.example.the_movie_db_test_task.ui.screens.FilmsScreen
import com.example.the_movie_db_test_task.utils.Constants

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Films : TabItem(Constants.films, { FilmsScreen() })
    object Favourites : TabItem(Constants.favorite, { FavouritesScreen() })
}