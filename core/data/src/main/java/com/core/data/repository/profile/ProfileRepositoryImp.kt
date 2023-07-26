package com.core.data.repository.profile

import android.net.Uri
import com.core.data.extension.toCurrentUser
import com.core.network.model.CurrentUserResponse
import com.core.network.profile.ProfileDataProvider
import com.example.model.CurrentUser
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val profileDataProvider: ProfileDataProvider
) : ProfileRepository {
    override suspend fun updateRemoteUser(userName: String, photo: Uri?): CurrentUser {
        return profileDataProvider.updateProfile(userName, photo).toCurrentUser()
    }
}