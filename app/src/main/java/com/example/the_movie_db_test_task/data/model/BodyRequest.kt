package com.example.the_movie_db_test_task.data.model

data class BodyRequest(
    var media_type: String = "movie",
    var media_id: Long,
    var favorite: Boolean = true
)