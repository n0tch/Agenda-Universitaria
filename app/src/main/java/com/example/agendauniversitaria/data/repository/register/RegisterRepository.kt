package com.example.agendauniversitaria.data.repository.register

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

interface RegisterRepository {

    suspend fun signupWithUserCredentials(email: String, password: String, photoUri: Uri?): FirebaseUser?
}