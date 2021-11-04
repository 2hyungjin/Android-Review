package com.example.sampleapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.sampleapp.network.local.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDataBase::class.java, "userDB")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(dataBase: UserDataBase) = dataBase.getUserDao()
}