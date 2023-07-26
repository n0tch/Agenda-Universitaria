package com.core.network.register

import com.core.network.model.CurrentUserResponse
import com.google.firebase.auth.FirebaseUser

interface RegisterDataProvider {

    suspend fun signUpWithCredentials(email: String, password: String): CurrentUserResponse

}