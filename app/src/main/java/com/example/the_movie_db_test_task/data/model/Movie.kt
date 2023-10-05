package com.example.the_movie_db_test_task.data.api.discaver

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    var page: Int = 1,
    var results: List<Movie>,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    var id: Long,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    var overview: String,
    @SerializedName("release_date")
    var releaseDate: String,
    var title: String,
    @SerializedName("vote_average")
    var voteAverage: Float
)