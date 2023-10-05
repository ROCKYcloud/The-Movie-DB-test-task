package com.example.the_movie_db_test_task.dataBase

import android.bluetooth.BluetoothClass.Device
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.the_movie_db_test_task.data.api.discaver.Movie

@Dao
interface MovieDao {

    @Delete
    suspend fun deleteFromFavorite(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(devices: List<Movie>)

    @Query("UPDATE movies SET title=:title WHERE id = :id")
    suspend fun update(title: String?, id: Long)
}