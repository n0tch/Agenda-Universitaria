package com.example.agendauniversitaria.network.profile

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

interface ProfileDataSource {

    suspend fun uploadImageProfile(imageUri: Uri?, userId: String): String

    suspend fun fetchUserImageUrl(userId: String): String

    suspend fun updateProfile(userName: String, photo: Uri?): FirebaseUser?

}