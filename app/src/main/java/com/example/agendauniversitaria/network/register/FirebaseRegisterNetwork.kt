package com.example.agendauniversitaria.network.register

import com.example.agendauniversitaria.network.register.FirebaseRegisterDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRegisterNetwork @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseRegisterDataSource {
    override suspend fun signUnWithCredentials(
        email: String,
        password: String
    ): FirebaseUser? = try {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
    } catch (exception: Exception) {
        throw exception
    }
}
