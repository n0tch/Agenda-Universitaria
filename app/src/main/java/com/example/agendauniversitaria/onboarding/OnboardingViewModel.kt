package com.example.agendauniversitaria.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<OnboardingState> by lazy {
        MutableStateFlow(OnboardingState.OnboardingIdle)
    }

    fun isUserLoggedIn() {
        viewModelScope.launch {
            userUseCase.isUserLoggedIn()
                .onStart { uiState.emit(OnboardingState.OnboardingIdle) }
                .collect {
                    when (it) {
                        is Result.Error -> uiState.emit(OnboardingState.OnboardingUserLoggedError(it.exception))
                        is Result.Success -> uiState.emit(OnboardingState.OnboardingUserLogged(it.data))
                    }
                }
        }
    }
}