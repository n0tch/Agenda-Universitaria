package com.example.agendauniversitaria.localstorage

import com.example.agendauniversitaria.common.Result
import kotlinx.coroutines.flow.Flow

interface StorageDataSource<T> {

    suspend fun insert(data: T): Result<Boolean>

    fun get(): Flow<T>
}
