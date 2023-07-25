package com.example.agendauniversitaria.domain.usecase

import android.net.Uri
import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.data.repository.profile.ProfileRepository
import com.example.agendauniversitaria.data.repository.register.RegisterRepository
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.network.model.toCurrentUserModel
import com.example.agendauniversitaria.network.user.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val registerRepository: RegisterRepository,
    private val profileRepository: ProfileRepository,
    private val userDataSource: UserDataSource
) {

    suspend fun registerUser(
        email: String,
        password: String,
        userName: String,
        photoUri: Uri?
    ): Result<CurrentUser> = try {
        registerRepository.signupWithUserCredentials(email, password, photoUri)
        val userUpdated = profileRepository.updateRemoteUser(userName, photoUri)

        val currentUser = userUpdated.toCurrentUserModel()
        userDataSource.saveUser(currentUser)

        Result.Success(currentUser)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
