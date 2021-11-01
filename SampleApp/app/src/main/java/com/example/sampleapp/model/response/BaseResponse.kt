package com.example.sampleapp.model.response

sealed class BaseResponse<T> {
    data class Success<T>(val data: T) : BaseResponse<T>()
    data class Failure<T>(val message: String) : BaseResponse<T>()
}
