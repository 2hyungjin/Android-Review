package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.model.entity.DataState
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyUserViewModel @Inject constructor(private val userLocalRepository: UserRepository) :
    ViewModel() {
    private val _userDataState = MutableLiveData<DataState<List<User>>>()
    val userDataState: LiveData<DataState<List<User>>> = _userDataState

    fun getUsers() {
        viewModelScope.launch {
            _userDataState.postValue(DataState.Loading)
            userLocalRepository.getMyUsers()
                .catch {
                    _userDataState.postValue(DataState.Failure("실패"))
                }
                .collect {
                    _userDataState.postValue(DataState.Success(it))
                }
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch {
            userLocalRepository.deleteUser(user)
        }
    }
}