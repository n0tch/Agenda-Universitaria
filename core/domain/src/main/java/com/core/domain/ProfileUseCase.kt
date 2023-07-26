package com.core.domain

import android.net.Uri
import com.core.common.Result
import com.core.data.repository.profile.ProfileRepository
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun updateProfileData(
        userName: String,
        photoUri: Uri?
    ): Result<CurrentUser> = try {
        val updateProfileResult = profileRepository.updateRemoteUser(userName, photoUri)
        Result.Success(updateProfileResult)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    fun resetPassword(email: String): Flow<Result<Boolean>> = flow {
        profileRepository.resetPassword(email)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }
}
