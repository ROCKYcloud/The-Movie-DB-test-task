package com.example.the_movie_db_test_task.data

import com.example.the_movie_db_test_task.data.model.discaver.ResponseMovie
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


//Ключ API
//a9cfabbe527a6111ab2f5c1d5fa8f3f3
//accaunt id = 20523672
//Токен доступу для читання API
//eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWNmYWJiZTUyN2E2MTExYWIyZjVjMWQ1ZmE4ZjNmMyIsInN1YiI6IjY1MWJjNTg5YzUwYWQyMDEwYmZmNGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gcIUonMVa7Q2r92rgvbo-RyvigSnP_VO4LMQ9KJvTPM
interface TMDBApi {
    @GET("3/discover/movie")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWNmYWJiZTUyN2E2MTExYWIyZjVjMWQ1ZmE4ZjNmMyIsInN1YiI6IjY1MWJjNTg5YzUwYWQyMDEwYmZmNGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gcIUonMVa7Q2r92rgvbo-RyvigSnP_VO4LMQ9KJvTPM")
    suspend fun getMovies(
        @Query("include_video") include_video: Boolean = true,
        @Query("include_adult") include_adult: Boolean = true,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET("3/account/{account_id}/favorite/movies")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWNmYWJiZTUyN2E2MTExYWIyZjVjMWQ1ZmE4ZjNmMyIsInN1YiI6IjY1MWJjNTg5YzUwYWQyMDEwYmZmNGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gcIUonMVa7Q2r92rgvbo-RyvigSnP_VO4LMQ9KJvTPM")
    suspend fun getFavorite(
        @Path("account_id") accountId: Long = 20523672,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<ResponseMovie>

    @POST("3/account/{account_id}/favorite")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWNmYWJiZTUyN2E2MTExYWIyZjVjMWQ1ZmE4ZjNmMyIsInN1YiI6IjY1MWJjNTg5YzUwYWQyMDEwYmZmNGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gcIUonMVa7Q2r92rgvbo-RyvigSnP_VO4LMQ9KJvTPM")
    suspend fun postFavorite(
        @Path("account_id") accountId: Long = 20523672,
        @Body request : BodyRequest

    ): Response<Map<String,Any>>
}

data class BodyRequest(
   var media_type:String = "movie",
   var media_id: Long,
   var favorite:Boolean = true

)