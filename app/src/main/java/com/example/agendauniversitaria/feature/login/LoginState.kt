package com.example.agendauniversitaria.feature.login

import com.example.agendauniversitaria.domain.model.CurrentUser

sealed class LoginState {
    object LoginLoading: LoginState()
    class LoginSuccess(val user: CurrentUser): LoginState()
    object LoginIdle: LoginState()
    class LoginFail(val exception: Exception): LoginState()
    object LoginFinish: LoginState()
}
