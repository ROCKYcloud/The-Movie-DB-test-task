package com.example.the_movie_db_test_task.di

import android.content.Context
import androidx.room.Room
import com.example.the_movie_db_test_task.data.TMDBApi
import com.example.the_movie_db_test_task.data.repository.Repository
import com.example.the_movie_db_test_task.dataBase.FavoriteMovieDataBase
import com.example.the_movie_db_test_task.dataBase.MovieDao
import com.example.the_movie_db_test_task.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDevicesRepository(
        api: TMDBApi,
        deviceDao: MovieDao,
    ) = Repository(api, deviceDao)

    @Provides
    fun provideMovieDao(appDatabase: FavoriteMovieDataBase): MovieDao {
        return appDatabase.getDevisesDao()
    }

    @Provides
    fun provideMovieApi(): TMDBApi {
        val client: OkHttpClient = Builder().addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${Constants.API_TOKEN}")
                .build()
            chain.proceed(newRequest)
        }).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBApi::class.java)
    }

    @Singleton
    @Provides
    fun createDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context.applicationContext,
        FavoriteMovieDataBase::class.java,
        "movie_db"
    ).fallbackToDestructiveMigration().build()
}
