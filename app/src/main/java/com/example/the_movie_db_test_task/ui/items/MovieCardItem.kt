package com.example.the_movie_db_test_task.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.the_movie_db_test_task.R
import com.example.the_movie_db_test_task.data.model.discaver.Movie

@Composable
fun MovieCardItem(
    movie: Movie,
    textFirstBat: String,
    textSecondBat: String = "share",
    onClickFirstBut: () -> Unit,
    onClickSecondBut: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        elevation = 16.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column() {
                AsyncImage(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(40.dp)
                        .height(40.dp),
                    model = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}",
                    contentDescription = "",
                    error = painterResource(R.drawable.ic_launcher_background),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xDE000000),
                    text = movie.voteAverage.toString(),
                )
            }
            Column() {
                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                Text(
                    text = movie.overview,
                    modifier = Modifier
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onClickFirstBut()
                    }) {
                        Text(
                            text = textFirstBat.uppercase(), modifier = Modifier
                        )
                    }
                    TextButton(onClick = { onClickSecondBut() }) {
                        Text(
                            text = textSecondBat.uppercase(),
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}