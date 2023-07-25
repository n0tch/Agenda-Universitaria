package com.example.agendauniversitaria.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.domain.usecase.LoginUseCase
import com.example.agendauniversitaria.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
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
            val currentUserResult = userUseCase.fetchCurrentUser()
            withContext(uiDispatcher){
                handleCurrentUserResult(currentUserResult)
            }
        }
    }

    private fun handleCurrentUserResult(currentUserResult: Result<CurrentUser>) = when (currentUserResult) {
        is Result.Error -> uiState.value = HomeState.HomeCurrentUserError(currentUserResult.exception)
        is Result.Success -> uiState.value = HomeState.HomeCurrentUser(currentUserResult.data)
    }

    fun logout() {
        viewModelScope.launch {
            loginUseCase.logout().combine(userUseCase.removeUser()) { firebase, local ->
                LogoutState.LogoutSuccess(
                    local = (local as Result.Success).data,
                    remote = (firebase as Result.Success).data
                )
            }
                .flowOn(uiDispatcher)
                .collect {
                    uiState.emit(LogoutState.LogoutSuccess(it.local, it.remote))
                }
        }
    }

}