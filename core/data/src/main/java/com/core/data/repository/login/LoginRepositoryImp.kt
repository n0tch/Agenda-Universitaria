package com.core.data.repository.login

import com.core.common.Result
import com.core.data.extension.toCurrentUser
import com.core.network.login.LoginDataProvider
import com.example.model.CurrentUser
import com.example.model.LoginSocialOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class LoginRepositoryImp @Inject constructor(
    private val loginDataProvider: LoginDataProvider
) : LoginRepository {

    override fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<CurrentUser>> =
        flow {
            loginDataProvider.loginWithCredentials(email, password).catch {
                emit(Result.Error(it as Exception))
            }.collect {
                emit(Result.Success(it.toCurrentUser()))
            }
        }

    override fun loginWithSocial(loginSocial: LoginSocialOption): Flow<Result<CurrentUser>> =
        flow {
            when (loginSocial) {
                LoginSocialOption.GOOGLE -> loginDataProvider.loginWithGoogle()
                LoginSocialOption.FACEBOOK -> loginDataProvider.loginWithFacebook()
                LoginSocialOption.ANONYMOUS -> loginDataProvider.loginAnonymous()
            }.catch {
                emit(Result.Error(it as Exception))
            }.collect {
                emit(Result.Success(it.toCurrentUser()))
            }
        }

    override fun logout(): Flow<Result<Boolean>> = flow {
        loginDataProvider
            .logout()
            .catch { emit(Result.Error(it as Exception)) }
            .collect { emit(Result.Success(it)) }
    }
}
