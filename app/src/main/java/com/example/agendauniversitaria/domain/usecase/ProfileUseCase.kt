package com.example.agendauniversitaria.domain.usecase

import android.net.Uri
import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.network.profile.ProfileDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val profileDataSource: ProfileDataSource
) {

    suspend fun uploadProfileImage(profileUri: Uri?, userId: String): String =
        profileDataSource.uploadImageProfile(profileUri, userId)
}