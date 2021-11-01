package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.model.response.BaseResponse
import com.example.sampleapp.network.repository.LoginRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val loginResult = MutableLiveData<Boolean>()
    private var job: Job? = null

    fun login(id: String, pw: String) {
        isLoading.postValue(true)
        job = viewModelScope.launch {
            when (loginRepository.login(id, pw)) {
                is BaseResponse.Success -> {
                    loginResult.postValue(true)
                }
                is BaseResponse.Failure -> {
                    throw Exception("로그인에 실패하였습니다.")
                }
            }
        }
    }
}