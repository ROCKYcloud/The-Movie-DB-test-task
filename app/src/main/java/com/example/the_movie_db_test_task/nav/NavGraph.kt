package com.example.the_movie_db_test_task.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.the_movie_db_test_task.ui.screens.AuthScreen
import com.example.the_movie_db_test_task.ui.screens.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Graph.AUTH_SCREEN.route) {
        composable(Graph.AUTH_SCREEN.route) {
            AuthScreen(){
                navController.navigate(Graph.MAIN_SCREEN.route)
            }
        }
        composable(Graph.MAIN_SCREEN.route) {
            MainScreen()
        }
    }
}

sealed class Graph(val route: String) {
    object AUTH_SCREEN : Graph(route = "auth_screen")
    object MAIN_SCREEN : Graph("main_screen")
}