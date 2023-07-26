package com.core.data.repository.profile

import android.net.Uri
import com.example.model.CurrentUser

interface ProfileRepository {

    suspend fun updateRemoteUser(userName: String, photo: Uri?): CurrentUser
}
