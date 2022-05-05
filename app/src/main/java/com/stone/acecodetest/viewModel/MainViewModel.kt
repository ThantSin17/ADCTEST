package com.stone.acecodetest.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.stone.acecodetest.model.User
import com.stone.acecodetest.repository.UserRepository
import com.stone.acecodetest.utils.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val apiResponse = MediatorLiveData<ApiResponse<Any>>()
    val allUsers: LiveData<List<User>> = repository.getUserFromDB.asLiveData()

    fun getUserList(): LiveData<ApiResponse<Any>> {
        apiResponse.addSource(
            repository.getAllUser()
        ) {
            apiResponse.value = it
        }
        return apiResponse
    }

    fun addPeople(people: List<User>) = CoroutineScope(Dispatchers.IO).launch {

        try {
            repository.insert(people)
            Log.i("mian", "b viewmodel")
        } catch (e: Exception) {
            Log.i("main", e.message.toString())
        }

    }

}

class MainViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}