package com.backbase.assignment.di

import android.content.Context
import com.backbase.assignment.data.remote.MovieServices
import com.backbase.assignment.data.remote.RetrofitBuilder
import com.backbase.assignment.repository.Repository
import com.backbase.assignment.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMovieServices(retrofitBuilder: RetrofitBuilder) : MovieServices{
        return retrofitBuilder.buildApi(MovieServices::class.java)
    }

    @Provides
    fun provideRepository(
        movieServices: MovieServices,
    ): Repository {
        return RepositoryImpl(
            movieServices = movieServices,
        )
    }
}