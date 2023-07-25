package com.example.agendauniversitaria.data.repository.login

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.LoginSocial
import com.example.agendauniversitaria.domain.model.UserCredentials
import com.example.agendauniversitaria.domain.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun loginWithEmailAndPassword(userCredentials: UserCredentials): Flow<Result<CurrentUser>>

    fun loginWithSocial(loginSocial: LoginSocial): Flow<Result<CurrentUser>>

    fun logout(): Flow<Result<Boolean>>
}
