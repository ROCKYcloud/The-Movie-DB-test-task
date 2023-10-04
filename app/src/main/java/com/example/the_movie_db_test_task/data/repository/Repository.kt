package com.example.the_movie_db_test_task.data.repository

import com.example.the_movie_db_test_task.data.BodyRequest
import com.example.the_movie_db_test_task.data.TMDBApi
import com.example.the_movie_db_test_task.data.model.discaver.ResponseMovie
import com.example.the_movie_db_test_task.utils.Resource
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: TMDBApi,
) {
    suspend fun getMovies(page:Int): Resource<ResponseMovie> {
        return try {
            val resp = api.getMovies(page=page)
           if (resp.isSuccessful){
               Resource.Success(resp.body())
           }else{
               println("")
           }
            resp.code()
            Resource.Success(resp.body()!!)
        } catch (e: Exception) {
            Resource.Error("$e")
        }
    }

    suspend fun getFavorites(): Resource<ResponseMovie> {
        return try {
            val resp = api.getFavorite()
            if (resp.isSuccessful){
                Resource.Success(resp.body())
            }else{
                println("")
            }
            resp.code()
            Resource.Success(resp.body()!!)
        } catch (e: Exception) {
            Resource.Error("$e")
        }
    }
    suspend fun postFavorites(movieId:Long, isAddToFavorite:Boolean): Resource<Map<String,Any>> {
        return try {
            val resp = api.postFavorite(request = BodyRequest(media_id = movieId, favorite = isAddToFavorite))
            if (resp.isSuccessful){
                Resource.Success(resp.code())
            }else{
                println("")
            }
            resp.code()
            Resource.Success(resp.body()!!)
        } catch (e: Exception) {
            Resource.Error("$e")
        }
    }
}