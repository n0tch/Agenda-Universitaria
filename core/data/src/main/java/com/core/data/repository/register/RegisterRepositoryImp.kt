package com.core.data.repository.register

import android.net.Uri
import com.core.common.Result
import com.core.network.model.CurrentUserResponse
import com.core.network.register.RegisterDataProvider
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(
    private val registerDataProvider: RegisterDataProvider
): RegisterRepository {
    override suspend fun signupWithUserCredentials(
        email: String,
        password: String,
        photoUri: Uri?
    ): Result<CurrentUserResponse> = try {
        val user = registerDataProvider.signUpWithCredentials(email, password)
        Result.Success(user)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
