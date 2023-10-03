package com.example.the_movie_db_test_task.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.the_movie_db_test_task.utils.Constants

@Composable
fun TabBar() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf(Constants.films.uppercase(), Constants.favorite.uppercase())
    Column {
        TabRow(selectedTabIndex = state, divider = {}) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
    }
}