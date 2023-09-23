package com.core.data.repository.user

import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchCurrentUser(): CurrentUser

    fun isUserLoggedIn(): Flow<Boolean>

    fun logout(): Flow<Boolean>

}
