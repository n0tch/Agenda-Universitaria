package com.example.agendauniversitaria.network.user

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    suspend fun fetchCurrentUser(): Result<CurrentUser>

    suspend fun saveUser(user: CurrentUser): Result<Boolean>

    fun isUserLoggedIn(): Flow<Result<Boolean>>

    fun logout(): Flow<Result<Boolean>>
}