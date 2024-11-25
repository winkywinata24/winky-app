package com.example.winky_app.basic_api.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.winky_app.basic_api.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        try {
            val response = repository.fetchUsers()
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
}