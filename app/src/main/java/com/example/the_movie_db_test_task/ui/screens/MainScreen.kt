package com.example.the_movie_db_test_task.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.the_movie_db_test_task.R
import com.example.the_movie_db_test_task.ui.items.TabBar
import com.example.the_movie_db_test_task.ui.theme.Purple500

@Composable
fun MainScreen() {
    Column {
        Row(
            modifier = Modifier
                .background(color = Purple500)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(40.dp)
                    .height(40.dp)
                    .clip(CircleShape),
                model = "",
                contentDescription = "",
                error = painterResource(R.drawable.ic_launcher_background),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )
        }
        TabBar()
    }
}