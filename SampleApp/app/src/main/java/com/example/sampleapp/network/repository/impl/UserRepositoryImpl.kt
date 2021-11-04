package com.example.sampleapp.network.repository.impl

import com.example.sampleapp.model.entity.User
import com.example.sampleapp.network.api.UserNetworkDataSource
import com.example.sampleapp.network.local.UserLocalDataSource
import com.example.sampleapp.network.repository.UserRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkDataSource: UserNetworkDataSource,
    private val localDataSource: UserLocalDataSource
) :
    UserRepository {

    override suspend fun getUser(id: String): Flow<User> = networkDataSource.getUser(id)

    override suspend fun getUsers(): Flow<List<User>> = networkDataSource.getUsers()

    override suspend fun getMyUsers(): Flow<List<User>> = flow {
        emit(localDataSource.getUsers())
    }

    override suspend fun addMyUser(user: User) {
        localDataSource.saveUser(user)
    }

    override suspend fun deleteUser(user: User) {
        localDataSource.deleteUser(user)
    }


}