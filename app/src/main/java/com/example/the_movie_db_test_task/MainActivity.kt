package com.example.the_movie_db_test_task

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.the_movie_db_test_task.ui.items.DataItem
import com.example.the_movie_db_test_task.ui.items.MovieCardItem
import com.example.the_movie_db_test_task.ui.items.TabItem
import com.example.the_movie_db_test_task.ui.screens.NavGraph
import com.example.the_movie_db_test_task.ui.theme.The_Movie_DB_test_taskTheme
import com.example.the_movie_db_test_task.utils.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            The_Movie_DB_test_taskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
@Composable
fun Greeting() {
    Column(Modifier.padding(16.dp)) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    The_Movie_DB_test_taskTheme {
        Greeting()
    }
}