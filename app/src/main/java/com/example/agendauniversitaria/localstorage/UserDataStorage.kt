package com.example.agendauniversitaria.localstorage

import com.example.agendauniversitaria.domain.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface UserDataStorage: StorageDataSource<CurrentUser> {

    fun isLogged(): Flow<Boolean>

    suspend fun getCurrentUser(): CurrentUser

    fun removeUser(): Flow<Boolean>

}