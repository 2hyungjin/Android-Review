package com.example.sampleapp.model.entity

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val message: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
