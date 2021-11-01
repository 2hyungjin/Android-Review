package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    protected var job: Job? = null
        set(value) {
            isLoading.postValue(true)
            field = value
        }
    val error = MutableLiveData<Throwable>()
    val isLoading = MutableLiveData<Boolean>()


    protected val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        isLoading.postValue(false)
        error.postValue(throwable)
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}