package com.example.the_movie_db_test_task.di

import com.example.the_movie_db_test_task.data.TMDBApi
import com.example.the_movie_db_test_task.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
//TODO interceptor
    @Provides
    fun provideMovieApi(): TMDBApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TMDBApi::class.java)
    }
}
