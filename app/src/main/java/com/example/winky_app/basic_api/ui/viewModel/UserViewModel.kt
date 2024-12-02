package com.example.winky_app.basic_api.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.winky_app.basic_api.data.model.User
import com.example.winky_app.basic_api.data.repository.UserRepository
import com.example.winky_app.basic_api.utils.NetworkUtils
import com.example.winky_app.basic_api.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    //    fun getUsers() = liveData(Dispatchers.IO) {
//        try {
//            val response = repository.fetchUsers()
//            emit(response)
//        } catch (e: Exception) {
//            emit(null)
//        }
//    }
    private val _data = MutableLiveData<Resource<List<User>>>()
    val data: LiveData<Resource<List<User>>> = _data

    fun getUsers(context: Context, forceRefresh: Boolean = false) {
        if (_data.value == null || forceRefresh) {
            if (NetworkUtils.isNetworkAvailable(context)) {
                viewModelScope.launch {
                    try {
                        _data.value = Resource.Loading()
                        delay(3000)
                        val response = repository.fetchUsers()
                        if (response.isEmpty()) {
                            _data.postValue(Resource.Empty("No Data Found"))
                        } else {
                            _data.postValue(Resource.Success(response))
                        }
                    } catch (e: Exception) {
                        _data.postValue(Resource.Error("Unknown Error : ${e.message}"))
                    }
                }
            } else {
                _data.postValue(Resource.Error("No Internet Connection"))
            }
        }
    }
}