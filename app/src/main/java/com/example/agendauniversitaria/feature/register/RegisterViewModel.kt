package com.example.agendauniversitaria.feature.register

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.domain.usecase.ProfileUseCase
import com.example.agendauniversitaria.domain.usecase.RegisterUseCase
import com.example.agendauniversitaria.domain.usecase.UserUseCase
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
    private val registerUseCase: RegisterUseCase,
    private val profileUseCase: ProfileUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<RegisterState> by lazy { MutableStateFlow(RegisterState.RegisterIdle) }

    fun register(email: String, password: String, userName: String, profileImage: Uri?) {
        uiState.value = RegisterState.RegisterLoading
        viewModelScope.launch(ioDispatcher) {
            val registerResult = registerUseCase.registerUser(email, password, userName, profileImage)

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