package com.core.network.register

import com.core.network.model.userResponse.CurrentUserResponse

interface RegisterDataProvider {

    suspend fun signUpWithCredentials(email: String, password: String): CurrentUserResponse

}