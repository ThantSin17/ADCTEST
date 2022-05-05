package com.stone.acecodetest


import android.app.Application
import com.stone.acecodetest.database.UserDatabase
import com.stone.acecodetest.network.ApiService
import com.stone.acecodetest.network.RetrofitApi
import com.stone.acecodetest.repository.UserRepository

class MyApplication:Application() {
    val retrofit by lazy {
        RetrofitApi.getInstance().create(ApiService::class.java)
    }
    val repository by lazy {
        UserRepository(retrofit,database.userDao())
    }
    val database by lazy {
        UserDatabase.getDatabase(this)
    }
}