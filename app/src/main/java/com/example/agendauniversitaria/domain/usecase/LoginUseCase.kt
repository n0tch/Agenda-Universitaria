package com.example.agendauniversitaria.domain.usecase

import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.data.repository.login.LoginRepository
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.domain.model.LoginSocial
import com.example.agendauniversitaria.domain.model.UserCredentials
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val loginRepository: LoginRepository
) {
    operator fun invoke(userCredentials: UserCredentials): Flow<Result<CurrentUser>> =
        loginRepository
            .loginWithEmailAndPassword(userCredentials)
            .flowOn(ioDispatcher)

    operator fun invoke(loginSocial: LoginSocial): Flow<Result<CurrentUser>> =
        loginRepository.loginWithSocial(loginSocial)
            .flowOn(ioDispatcher)

    fun logout(): Flow<Result<Boolean>> =
        loginRepository
            .logout()
            .flowOn(ioDispatcher)
}