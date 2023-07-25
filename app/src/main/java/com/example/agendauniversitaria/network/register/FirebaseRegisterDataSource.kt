package com.example.agendauniversitaria.network.register

import com.google.firebase.auth.FirebaseUser

interface FirebaseRegisterDataSource {

    suspend fun signUnWithCredentials(email: String, password: String): FirebaseUser?

}