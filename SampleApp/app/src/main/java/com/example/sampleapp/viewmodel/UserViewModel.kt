package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.network.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private var _users = listOf<User>()
        set(value) = users.postValue(value)

    val users = MutableLiveData<List<User>>()



    fun getUser(id: String) {
        job = viewModelScope.launch {
            val result = userRepository.getUser(id)
            if (result.isSuccessful) {
                val user = result.body()?.toEntity()
                _users = listOf(user!!)
                isLoading.postValue(false)
            } else {
                throw Exception("해당하는 유저를 찾지 못했습니다.")
            }
        }
    }

    fun getUsers() {
        job = viewModelScope.launch {
            val result = userRepository.getUsers()
            if (result.isSuccessful) {
                val users = result.body()?.map { it.toEntity() }
                _users = users!!
                isLoading.postValue(false)
            } else {
                throw Exception("사용자를 불러오지 못했습니다.")
            }
        }
    }

//    fun getUsersFromRoom() {
//        job = viewModelScope.launch {
//            val result=userRepository.getUsersFromRoom()
//            when(result){
//                is BaseResponse.Success<>
//            }
//        }
//    }

}