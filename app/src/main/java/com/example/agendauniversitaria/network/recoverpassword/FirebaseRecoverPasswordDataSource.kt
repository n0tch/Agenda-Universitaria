package com.example.agendauniversitaria.network.recoverpassword

import com.example.agendauniversitaria.common.Result
import kotlinx.coroutines.flow.Flow

interface FirebaseRecoverPasswordDataSource {

    fun resetPassword(email: String): Flow<Result<Boolean>>

}