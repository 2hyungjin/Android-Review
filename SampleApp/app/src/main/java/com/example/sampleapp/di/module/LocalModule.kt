package com.example.sampleapp.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.network.local.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDataBase =
        Room.databaseBuilder(context, UserDataBase::class.java, "userDB").build()
    @Singleton
    @Provides
    fun provideUserDao(dataBase: UserDataBase) = dataBase.getUserDao()
}