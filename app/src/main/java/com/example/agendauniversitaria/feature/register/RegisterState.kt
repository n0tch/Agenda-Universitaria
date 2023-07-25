package com.example.agendauniversitaria.feature.register

import com.example.agendauniversitaria.domain.model.CurrentUser

sealed class RegisterState{
    object RegisterLoading: RegisterState()
    class RegisterSuccess(val user: CurrentUser): RegisterState()
    object RegisterIdle: RegisterState()
    class RegisterFail(val exception: Exception): RegisterState()
}
