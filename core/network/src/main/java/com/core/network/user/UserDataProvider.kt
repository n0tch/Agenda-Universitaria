package com.core.network.user

import com.core.network.model.userResponse.CurrentUserResponse
import kotlinx.coroutines.flow.Flow

interface UserDataProvider {

    suspend fun fetchCurrentUser(): CurrentUserResponse

    fun isUserLoggedIn(): Flow<Boolean>

    fun logout(): Flow<Boolean>

}
