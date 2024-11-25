package com.example.winky_app.basic_api.data.network

import com.example.winky_app.basic_api.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}