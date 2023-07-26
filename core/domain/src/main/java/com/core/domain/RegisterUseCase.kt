package com.core.domain

import com.core.common.Result
import com.core.data.repository.register.RegisterRepository
import com.example.model.CurrentUser
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {

    suspend fun registerUser(
        email: String,
        password: String
    ): Result<CurrentUser> = try {
        val registerResult = registerRepository.signupWithUserCredentials(email, password)
        Result.Success(registerResult)
    } catch (exception: Exception) {
        Result.Error(exception)
    }
}
