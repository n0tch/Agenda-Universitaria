package com.core.data.repository.register

import android.net.Uri
import com.example.model.CurrentUser

interface RegisterRepository {

    suspend fun signupWithUserCredentials(
        email: String,
        password: String
    ): CurrentUser

}
