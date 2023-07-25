package com.example.agendauniversitaria.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.domain.model.LoginSocial
import com.example.agendauniversitaria.domain.model.UserCredentials
import com.example.agendauniversitaria.domain.usecase.LoginUseCase
import com.example.agendauniversitaria.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<LoginState> by lazy { MutableStateFlow(LoginState.LoginIdle) }

    fun loginWithCredentials(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(UserCredentials(email, password))
                .onStart {
                    uiState.emit(LoginState.LoginLoading)
                }
                .flowOn(uiDispatcher)
                .collectLatest { result ->
                    when(result){
                        is Result.Error -> {
                            uiState.emit(LoginState.LoginFail(result.exception))
                        }
                        is Result.Success -> {
                            uiState.emit(LoginState.LoginSuccess(result.data))
                        }
                    }
                }
        }
    }

    fun loginWithSocial(loginSocial: LoginSocial){
        viewModelScope.launch {
            loginUseCase(loginSocial)
                .onStart { uiState.emit(LoginState.LoginLoading) }
                .flowOn(uiDispatcher)
                .collect { result ->
                    when(result){
                        is Result.Error -> {
                            uiState.emit(LoginState.LoginFail(result.exception))
                        }
                        is Result.Success -> {
                            uiState.emit(LoginState.LoginSuccess(result.data))
                        }
                    }
                }
        }
    }

    fun saveUser(currentUser: CurrentUser){
        viewModelScope.launch(ioDispatcher) {
            val saveUSerResult = userUseCase.saveUser(currentUser)
            withContext(uiDispatcher){
                handleUserSaveResult(saveUSerResult)
            }
        }
    }

    private fun handleUserSaveResult(saveUSerResult: Result<Boolean>) {
        when(saveUSerResult){
            is Result.Error -> uiState.value = LoginState.LoginFail(saveUSerResult.exception)
            is Result.Success -> uiState.value = LoginState.LoginFinish
        }
    }

}