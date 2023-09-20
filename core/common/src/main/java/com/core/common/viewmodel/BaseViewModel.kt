package com.core.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<A : Action, N : Navigation> : ViewModel() {

    private val _actionState: MutableLiveData<A?> = MutableLiveData(null)
    val actionState: LiveData<A?> = _actionState

    private val _navigationState: MutableStateFlow<N?> = MutableStateFlow(null)
    val navigationState: StateFlow<N?> = _navigationState.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000L), null)

    fun setAction(action: A?) {
        viewModelScope.launch {
            _actionState.postValue(action)
        }
    }

    fun setNavigation(navigation: N?) {
        viewModelScope.launch {
            _navigationState.update { navigation }
            delay(100L)
            _navigationState.update { null }
        }
    }
}