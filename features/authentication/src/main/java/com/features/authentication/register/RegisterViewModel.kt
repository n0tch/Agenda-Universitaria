package com.features.authentication.register

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.RegisterUseCase
import com.example.model.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<RegisterState> by lazy { MutableStateFlow(RegisterState.RegisterIdle) }

    fun register(email: String, password: String, userName: String, profileImage: Uri?) {
        uiState.value = RegisterState.RegisterLoading
        viewModelScope.launch(ioDispatcher) {
            val registerResult = registerUseCase.registerUser(email, password, userName, profileImage)

            handleRegisterResult(registerResult)
            withContext(uiDispatcher){
                handleRegisterResult(registerResult)
            }
        }
    }

    private fun handleRegisterResult(registerResult: Result<CurrentUser>) = when(registerResult){
        is Result.Error -> uiState.value = RegisterState.RegisterFail(registerResult.exception)
        is Result.Success -> uiState.value = RegisterState.RegisterSuccess(registerResult.data)
    }
}