package com.core.domain

import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.login.LoginRepository
import com.example.model.CurrentUser
import com.example.model.LoginSocialOption
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val loginRepository: LoginRepository
) {
    operator fun invoke(email: String, password: String): Flow<Result<CurrentUser>> =
        loginRepository
            .loginWithEmailAndPassword(email, password)
            .flowOn(ioDispatcher)

    operator fun invoke(loginSocial: LoginSocialOption): Flow<Result<CurrentUser>> =
        loginRepository.loginWithSocial(loginSocial)
            .flowOn(ioDispatcher)

    fun logout(): Flow<Result<Boolean>> =
        loginRepository
            .logout()
            .flowOn(ioDispatcher)
}