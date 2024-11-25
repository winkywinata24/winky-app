package com.example.winky_app.basic_api.data.repository

import com.example.winky_app.basic_api.data.model.User
import com.example.winky_app.basic_api.data.network.ApiService

class UserRepository (private val api: ApiService) {
    suspend fun fetchUsers(): List<User> {
        return api.getUsers()
    }
}