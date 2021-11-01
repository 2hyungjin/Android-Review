package com.example.sampleapp.model.response

import com.example.sampleapp.model.entity.User

data class UserResponse(
    val login: String,
    val avatar_url: String,
    val followers: Int,
    val following: Int,
) {
    fun toEntity(): User = User(login, avatar_url, followers, following)
}
