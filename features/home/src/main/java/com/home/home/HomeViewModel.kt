package com.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.LoginUseCase
import com.core.domain.UserUseCase
import com.example.model.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    init {
        fetchCurrentUser()
    }

    val uiState: MutableStateFlow<HomeState> by lazy { MutableStateFlow(HomeState.HomeIdle) }

    fun fetchCurrentUser() {
        viewModelScope.launch(ioDispatcher) {
            userUseCase.fetchCurrentUser().flowOn(uiDispatcher).collect{
                when(it){
                    is Result.Error -> uiState.value = HomeState.HomeCurrentUserError(it.exception)
                    is Result.Success -> uiState.value = HomeState.HomeCurrentUser(it.data)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            loginUseCase.logout()
                .flowOn(uiDispatcher)
                .onStart { uiState.emit(HomeState.HomeIdle) }
                .collect {
                    when(it){
                        is Result.Error -> uiState.emit(LogoutState.LogoutError(it.exception))
                        is Result.Success -> uiState.emit(LogoutState.LogoutSuccess(it.data))
                    }
                }
        }
    }

}