package com.example.sampleapp.network.api

import com.example.sampleapp.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class UserNetworkDataSource @Inject constructor(private val userService: UserService) {
    suspend fun getUser(id: String): Flow<User> {
        return flow {
            emit(userService.getUser(id).toEntity())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUsers(): Flow<List<User>> {
        return flow {
            emit(userService.getUsers().map { it.toEntity() })
        }.flowOn(Dispatchers.IO)
    }
}