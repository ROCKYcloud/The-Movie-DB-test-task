package com.example.the_movie_db_test_task.ui.items

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun UpdateUI(text :String, onClick:()->Unit){
    Text(text = text)
    Button(
        onClick = {
            onClick()
        }) {
        Text(text = "Update")
    }
}