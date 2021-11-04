package com.example.sampleapp.viewmodel

import android.util.Log
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

    private val _usersDataState = MutableLiveData<DataState<List<User>>>()

    val usersDataState: LiveData<DataState<List<User>>> = _usersDataState


    fun getUser(id: String) {
        viewModelScope.launch {
            _usersDataState.postValue(DataState.Loading)
            userRepository.getUser(id)
                .catch {
                    _usersDataState.postValue(DataState.Failure("user is not found"))
                }
                .collect {
                    _usersDataState.postValue(DataState.Success(listOf(it)))
                }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            _usersDataState.postValue(DataState.Loading)
            userRepository.getUsers()
                .catch {
                    _usersDataState.postValue(DataState.Failure("aaa"))
                }
                .collect {
                    _usersDataState.postValue(DataState.Success(it))
                }

        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addMyUser(user)
        }
    }

}