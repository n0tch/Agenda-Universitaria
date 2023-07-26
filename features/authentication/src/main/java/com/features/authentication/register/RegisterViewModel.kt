package com.features.authentication.register

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.ProfileUseCase
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
    private val registerUseCase: RegisterUseCase,
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<RegisterState> by lazy { MutableStateFlow(RegisterState.RegisterIdle) }

    fun register(email: String, password: String, userName: String, profileImage: Uri?) {
        uiState.value = RegisterState.RegisterLoading
        viewModelScope.launch(ioDispatcher) {
            val registerResult =
                registerUseCase.registerUser(email, password)

            handleRegisterResult(registerResult, userName, profileImage)
        }
    }

    private suspend fun handleRegisterResult(
        registerResult: Result<CurrentUser>,
        userName: String,
        profileImage: Uri?
    ) {
        when (registerResult) {
            is Result.Error -> {
                withContext(uiDispatcher) {
                    uiState.value = RegisterState.RegisterFail(registerResult.exception)
                }
            }

            is Result.Success -> {
                val profileUpdateResult = profileUseCase.updateProfileData(userName, profileImage)
                withContext(uiDispatcher) {
                    handleProfileUpdateResult(profileUpdateResult)
                }
            }
        }
    }

    private fun handleProfileUpdateResult(profileUpdateResult: Result<CurrentUser>) =
        when (profileUpdateResult) {
            is Result.Error -> uiState.value =
                RegisterState.RegisterFail(profileUpdateResult.exception)

            is Result.Success -> uiState.value =
                RegisterState.RegisterSuccess(profileUpdateResult.data)
        }
}
