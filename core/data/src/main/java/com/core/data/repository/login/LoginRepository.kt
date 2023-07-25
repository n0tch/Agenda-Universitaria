package com.core.data.repository.login

import com.core.common.Result
import com.example.model.CurrentUser
import com.example.model.LoginSocialOption
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun loginWithEmailAndPassword(email: String, password: String): Flow<Result<CurrentUser>>

    fun loginWithSocial(loginSocial: LoginSocialOption): Flow<Result<CurrentUser>>

    fun logout(): Flow<Result<Boolean>>
}
