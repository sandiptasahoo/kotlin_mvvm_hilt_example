package com.backbase.assignment.data.remote

import com.backbase.assignment.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitBuilder @Inject constructor(){

    fun buildApi(services: Class<MovieServices>) : MovieServices{
        return Retrofit.Builder()
            .baseUrl(Constants.MOVIE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(services)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    private val gson = GsonBuilder()
        .setLenient()
        .create()
}