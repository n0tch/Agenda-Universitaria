package com.features.authentication.register

import com.example.model.CurrentUser

sealed class RegisterState{
    object RegisterLoading: RegisterState()
    class RegisterSuccess(val user: CurrentUser): RegisterState()
    object RegisterIdle: RegisterState()
    class RegisterFail(val exception: Exception): RegisterState()
}
