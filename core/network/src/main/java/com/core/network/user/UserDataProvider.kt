package com.core.network.user

import com.core.network.model.CurrentUserResponse
import kotlinx.coroutines.flow.Flow

interface UserDataProvider {

    suspend fun fetchCurrentUser(): Flow<CurrentUserResponse>

    fun isUserLoggedIn(): Flow<Boolean>

    fun logout(): Flow<Boolean>

}