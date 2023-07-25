package com.example.agendauniversitaria.data.repository.profile

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

interface ProfileRepository {

    suspend fun updateRemoteUser(userName: String, photo: Uri?): FirebaseUser?

}