package com.example.sampleapp.network.repository

import com.example.sampleapp.model.response.BaseResponse

interface LoginRepository {
    suspend fun login(id: String, pw: String): BaseResponse<String>
}