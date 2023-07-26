package com.core.domain

import android.net.Uri
import com.core.common.Result
import com.core.data.repository.profile.ProfileRepository
import com.core.data.repository.register.RegisterRepository
import com.example.model.CurrentUser
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val profileRepository: ProfileRepository
) {

    suspend fun registerUser(
        email: String,
        password: String,
        userName: String,
        photoUri: Uri?
    ): Result<CurrentUser> = try {

        val registerResult = registerRepository.signupWithUserCredentials(email, password, photoUri)

        if(registerResult is Result.Error){
            Result.Error(registerResult.exception)
        } else {
            val userUpdated = profileRepository.updateRemoteUser(userName, photoUri)
            Result.Success(userUpdated)
        }
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
