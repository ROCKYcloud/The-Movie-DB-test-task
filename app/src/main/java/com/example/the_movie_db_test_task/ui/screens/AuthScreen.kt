package com.example.the_movie_db_test_task.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthScreen(onClick:()->Unit) {
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Bottom) {

        Button(onClick = {
        onClick()
        }) {
            Text(text = "Continue with facebook")
        }
    }
}