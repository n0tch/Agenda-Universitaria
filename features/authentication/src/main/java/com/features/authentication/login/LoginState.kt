package com.features.authentication.login

sealed class LoginState {
    object LoginLoading: LoginState()
    object LoginIdle: LoginState()
    class LoginFail(val exception: Exception): LoginState()
    object LoginFinish: LoginState()
}
