package com.stone.acecodetest.network

import com.stone.acecodetest.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getAllUsers() : Call<List<User>>


}