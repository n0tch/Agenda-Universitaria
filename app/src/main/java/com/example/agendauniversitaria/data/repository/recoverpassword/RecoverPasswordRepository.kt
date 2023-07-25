package com.example.agendauniversitaria.data.repository.recoverpassword

import com.example.agendauniversitaria.common.Result
import kotlinx.coroutines.flow.Flow

interface RecoverPasswordRepository {

    fun recoverPassword(email: String): Flow<Result<Boolean>>

}