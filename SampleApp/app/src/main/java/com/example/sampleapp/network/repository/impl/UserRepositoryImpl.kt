package com.example.sampleapp.network.repository.impl

import com.example.sampleapp.model.entity.User
import com.example.sampleapp.network.api.UserService
import com.example.sampleapp.network.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) :
    UserRepository {
    override suspend fun getUser(id: String): Flow<User> =
        flow {
            val user = userService.getUser(id).toEntity()
            emit(user)
        }.flowOn(Dispatchers.IO)

    override suspend fun getUsers(): Flow<List<User>> =
        flow {
            val users = userService.getUsers().map { it.toEntity() }
            emit(users)
        }
}