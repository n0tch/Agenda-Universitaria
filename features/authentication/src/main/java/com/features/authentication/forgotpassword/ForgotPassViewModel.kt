package com.features.authentication.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<ForgotPassState> by lazy { MutableStateFlow(ForgotPassState.Idle) }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            profileUseCase.resetPassword(email)
                .onStart { uiState.emit(ForgotPassState.Loading) }
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> uiState.emit(ForgotPassState.Error(it.exception))
                        is Result.Success -> uiState.emit(ForgotPassState.Success(it.data))
                    }
                }
        }
    }

}