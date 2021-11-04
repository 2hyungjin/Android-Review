package com.example.sampleapp.network.local

import com.example.sampleapp.model.entity.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao) {
    suspend fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    suspend fun saveUser(user: User) {
        userDao.saveUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}