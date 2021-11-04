package com.example.sampleapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: String,
    val avatar: String,
    val follower: Int,
    val following: Int,
)
