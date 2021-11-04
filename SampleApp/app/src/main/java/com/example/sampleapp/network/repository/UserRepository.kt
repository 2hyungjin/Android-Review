package com.example.sampleapp.network.repository

import com.example.sampleapp.model.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(id: String): Flow<User>
    suspend fun getUsers(): Flow<List<User>>
    suspend fun getMyUsers(): Flow<List<User>>

    suspend fun addMyUser(user: User)
    suspend fun deleteUser(user: User)
}