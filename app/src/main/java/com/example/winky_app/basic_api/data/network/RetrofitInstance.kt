package com.example.winky_app.basic_api.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URLJSONPLACEHOLDER = "https://jsonplaceholder.typicode.com"

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun getJsonPlaceHolderApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URLJSONPLACEHOLDER)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}