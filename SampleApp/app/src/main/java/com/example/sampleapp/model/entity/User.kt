package com.example.sampleapp.model.entity

data class User(
    val id: String,
    val avatar: String,
    val follower: Int,
    val following: Int,
)
