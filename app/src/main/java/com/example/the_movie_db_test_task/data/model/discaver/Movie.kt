package com.example.the_movie_db_test_task.data.model.discaver

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    var page: Int =1 ,
    var results: List<Movie>,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)

data class Movie(
    var id: Long,
//    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String,
//    @SerializedName("genre_ids")
//    var genreIds: List<Int>? = null,
//    @SerializedName("original_language")
//    var originalLanguage: String? = null,
//    @SerializedName("original_title")
//    var originalTitle: String? = null,
    var overview: String,
//    var popularity: Double? = null,
//    @SerializedName("poster_path")
//    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String,
    var title: String,
//    var video: Boolean? = null,
    @SerializedName("vote_average")
    var voteAverage: Float,
//    @SerializedName("vote_count")
//    var voteCount: Int? = null
)