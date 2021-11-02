package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.model.entity.DataState
import com.example.sampleapp.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private var _usersUiState = MutableLiveData<DataState<List<User>>>()
    val usersUiState: LiveData<DataState<List<User>>> = _usersUiState


    fun getUser(id: String) {
        viewModelScope.launch {
            _usersUiState.postValue(DataState.Loading)
            userRepository.getUser(id)
                .catch {
                    _usersUiState.postValue(DataState.Failure(""))
                }
                .collect {
                    _usersUiState.postValue(DataState.Success(listOf(it)))
                }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            _usersUiState.postValue(DataState.Loading)
            userRepository.getUsers()
                .catch {
                    _usersUiState.postValue(DataState.Failure("aaa"))
                }
                .collect {
                    _usersUiState.postValue(DataState.Success(it))
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