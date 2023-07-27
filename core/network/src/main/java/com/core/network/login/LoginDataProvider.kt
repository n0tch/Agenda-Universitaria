package com.core.network.login

import com.core.network.model.userResponse.CurrentUserResponse
import kotlinx.coroutines.flow.Flow

interface LoginDataProvider {

    fun loginWithCredentials(email: String, password: String): Flow<CurrentUserResponse>

    fun loginWithGoogle(): Flow<CurrentUserResponse>

    fun loginWithFacebook(): Flow<CurrentUserResponse>

    fun loginAnonymous(): Flow<CurrentUserResponse>

    fun logout(): Flow<Boolean>

}