package com.example.the_movie_db_test_task.ui.items

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DataItem(){
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = "Fab 2021"
    )
}