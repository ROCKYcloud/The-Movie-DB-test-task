package com.example.the_movie_db_test_task.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.the_movie_db_test_task.data.api.discaver.Movie

@Database(entities = [Movie::class], version = 1,exportSchema = false)
abstract class FavoriteMovieDataBase : RoomDatabase() {
    abstract fun getDevisesDao(): MovieDao
}