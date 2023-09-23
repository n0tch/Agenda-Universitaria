package com.core.data.repository.register

import com.example.model.CurrentUser

interface RegisterRepository {

    suspend fun signupWithUserCredentials(
        email: String,
        password: String
    ): CurrentUser

}
