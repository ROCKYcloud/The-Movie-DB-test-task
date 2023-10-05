package com.example.the_movie_db_test_task.dbRoom

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.the_movie_db_test_task.data.model.discaver.Movie
import com.example.the_movie_db_test_task.dataBase.FavoriteMovieDataBase
import com.example.the_movie_db_test_task.dataBase.MovieDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import android.content.Context

@RunWith(AndroidJUnit4ClassRunner::class)
class DevisesDataBaseTest : TestCase() {
    private lateinit var movieDao: MovieDao
    private lateinit var db: FavoriteMovieDataBase

    @Test
    fun insertMovies() = runBlocking {
        movieDao.insert(getMovies())
        val devises = movieDao.getAllMovies()
        Assert.assertEquals(devises.isNotEmpty(), true)
    }

    @Test
    fun deleteMovie() = runBlocking {
        movieDao.insert(getMovies())
        movieDao.deleteFromFavorite(getMovie())
        val devices = movieDao.getAllMovies()
        Assert.assertEquals(!devices.contains(getMovie()), true)
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, FavoriteMovieDataBase::class.java
        ).build()
        movieDao = db.getDevisesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    private fun getMovie() = Movie(
        id = 1,
        backdropPath = "some Image",
        overview = " description",
        releaseDate = "21-02-2001",
        title = "title Some movie",
        voteAverage = 7.1f,
    )

    private fun getMovies() = listOf(
        Movie(
            id = 1,
            backdropPath = "some Image",
            overview = " description",
            releaseDate = "21-02-2001",
            title = "title Some movie",
            voteAverage = 7.1f,
        ),
        Movie(
            id = 2,
            backdropPath = "some Image",
            overview = " description",
            releaseDate = "21-02-2001",
            title = "title Some movie2",
            voteAverage = 7.1f,
        )
    )
}