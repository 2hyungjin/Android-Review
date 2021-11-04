package com.example.sampleapp.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleapp.model.entity.User


@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}