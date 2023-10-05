package com.example.the_movie_db_test_task.data

import com.example.the_movie_db_test_task.data.api.discaver.ResponseMovie
import com.example.the_movie_db_test_task.data.model.BodyRequest
import com.example.the_movie_db_test_task.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("discover/movie")
    @Headers(Constants.API_TOKEN)
    suspend fun getMovies(
        @Query("include_video") include_video: Boolean = true,
        @Query("include_adult") include_adult: Boolean = true,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "primary_release_date.desc",
        @Query("vote_average.gte") vote_average: Float = 3.4f,
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET("account/{account_id}/favorite/movies")
    @Headers(Constants.API_TOKEN)
    suspend fun getFavorite(
        @Path("account_id") accountId: Int = Constants.accountId,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<ResponseMovie>

    @POST("account/{account_id}/favorite")
    @Headers(Constants.API_TOKEN)
    suspend fun postFavorite(
        @Path("account_id") accountId: Int = Constants.accountId,
        @Body request: BodyRequest
    ): Response<Map<String, Any>>
}

