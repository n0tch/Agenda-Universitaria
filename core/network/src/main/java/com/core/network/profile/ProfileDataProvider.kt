package com.core.network.profile

import android.net.Uri
import com.core.network.model.CurrentUserResponse

interface ProfileDataProvider {

    suspend fun updateProfile(userName: String, photo: Uri?): CurrentUserResponse

}