package com.example.sampleapp.network.local

import androidx.room.*
import com.example.sampleapp.model.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}