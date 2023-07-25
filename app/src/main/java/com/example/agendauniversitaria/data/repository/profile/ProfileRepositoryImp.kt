package com.example.agendauniversitaria.data.repository.profile

import android.net.Uri
import com.example.agendauniversitaria.data.repository.profile.ProfileRepository
import com.example.agendauniversitaria.network.profile.ProfileDataSource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun updateRemoteUser(userName: String, photo: Uri?): FirebaseUser? {
        return profileDataSource.updateProfile(userName, photo)
    }
}