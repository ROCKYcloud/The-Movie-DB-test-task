package com.example.the_movie_db_test_task.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(onClick:()->Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.Bottom) {

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
        onClick()
        }) {
            Text(text = "Continue with facebook")
        }
    }
}