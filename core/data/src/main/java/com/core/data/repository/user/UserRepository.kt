package com.core.data.repository.user

import com.core.common.Result
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchCurrentUser(): Flow<CurrentUser>

    fun isUserLoggedIn(): Flow<Boolean>

    fun logout(): Flow<Boolean>

}