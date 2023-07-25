package com.example.agendauniversitaria.feature.forgotpass

sealed class ForgotPassState{
    object Loading: ForgotPassState()
    object Idle: ForgotPassState()
    class Success(val isSuccess: Boolean): ForgotPassState()
    class Error(val exception: Exception): ForgotPassState()
}
