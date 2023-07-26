package com.core.data.repository.register

import com.core.data.extension.toCurrentUser
import com.core.network.register.RegisterDataProvider
import com.example.model.CurrentUser
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(
    private val registerDataProvider: RegisterDataProvider
) : RegisterRepository {
    override suspend fun signupWithUserCredentials(
        email: String,
        password: String
    ): CurrentUser = try {
        val user = registerDataProvider.signUpWithCredentials(email, password)
        user.toCurrentUser()
    } catch (exception: Exception) {
        throw exception
    }
}
