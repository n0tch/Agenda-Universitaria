package com.features.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Result
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.domain.LoginUseCase
import com.example.model.LoginSocialOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<LoginState> by lazy { MutableStateFlow(LoginState.LoginIdle) }

    fun loginWithCredentials(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password)
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
                            uiState.emit(LoginState.LoginFinish)
                        }
                    }
                }
        }
    }

    fun loginWithSocial(loginSocial: LoginSocialOption){
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
                            uiState.emit(LoginState.LoginFinish)
                        }
                    }
                }
        }
    }
}
