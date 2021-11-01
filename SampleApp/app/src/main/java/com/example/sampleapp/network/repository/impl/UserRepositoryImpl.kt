package com.example.sampleapp.network.repository.impl

import com.example.sampleapp.model.response.UserResponse
import com.example.sampleapp.network.api.UserService
import com.example.sampleapp.network.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) :
    UserRepository {
    override suspend fun getUser(id: String): Response<UserResponse> = userService.getUser(id)

    override suspend fun getUsers(): Response<List<UserResponse>> = userService.getUsers()

//    override suspend fun getUsersFromRoom(): List<UserResponse> = userService.getUsers()
}