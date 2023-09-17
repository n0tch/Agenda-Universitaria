package com.home.home.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<A : Action, N : Navigation> : ViewModel() {

    private val _actionState: MutableLiveData<A> = MutableLiveData()
    val actionState: LiveData<A> = _actionState

    private val _navigationState: MutableStateFlow<N?> = MutableStateFlow(null)
    val navigationState: StateFlow<N?> = _navigationState.asStateFlow()

    fun setAction(action: A) {
        viewModelScope.launch {
            _actionState.postValue(action)
        }
    }

    fun setNavigation(navigation: N) {
        viewModelScope.launch { _navigationState.update { navigation } }
    }
}