package com.example.sampleapp.network.api

import com.example.sampleapp.model.response.UserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): UserResponse

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}