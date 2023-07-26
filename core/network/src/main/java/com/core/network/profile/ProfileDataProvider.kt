package com.core.network.profile

import android.net.Uri
import com.core.network.model.CurrentUserResponse
import kotlinx.coroutines.flow.Flow

interface ProfileDataProvider {

    suspend fun updateProfile(userName: String, photo: Uri?): CurrentUserResponse

    fun resetPassword(email: String): Flow<Boolean>
}