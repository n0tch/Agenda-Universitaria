package com.core.data.repository.profile

import android.net.Uri
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun updateRemoteUser(userName: String, photo: Uri?): CurrentUser

    suspend fun resetPassword(email: String): Flow<Boolean>
}
