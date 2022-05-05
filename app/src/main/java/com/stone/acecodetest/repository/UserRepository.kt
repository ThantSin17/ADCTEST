package com.stone.acecodetest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stone.acecodetest.database.PersonDao
import com.stone.acecodetest.model.User
import com.stone.acecodetest.network.ApiService
import com.stone.acecodetest.utils.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService: ApiService, private val personDao: PersonDao) {

    fun getAllUser(): LiveData<ApiResponse<Any>> {
        var result = MutableLiveData<ApiResponse<Any>>()
        apiService.getAllUsers().enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        result.postValue(ApiResponse.Success(it))
                    }
                } else {
                    result.postValue(ApiResponse.Error("factal errror"))
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                result.postValue(ApiResponse.NetWorkError("network error"))
            }

        })
        return result
    }

    fun insert(users: List<User>) {
        Log.i("mian", "b repo")
        for (user in users) {
            personDao.insertPerson(user)
        }
    }

    val getUserFromDB = personDao.getAllUser()

}

