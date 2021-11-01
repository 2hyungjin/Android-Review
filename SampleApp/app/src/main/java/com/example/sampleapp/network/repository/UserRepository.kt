package com.example.sampleapp.network.repository

import com.example.sampleapp.model.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

interface UserRepository {
    suspend fun getUser(id: String): Response<UserResponse>
    suspend fun getUsers(): Response<List<UserResponse>>
//    suspend fun getUsersFromRoom(): List<UserResponse>
}