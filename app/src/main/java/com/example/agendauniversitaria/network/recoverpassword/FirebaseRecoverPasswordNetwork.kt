package com.example.agendauniversitaria.network.recoverpassword

import com.example.agendauniversitaria.common.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRecoverPasswordNetwork @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FirebaseRecoverPasswordDataSource {
    override fun resetPassword(email: String): Flow<Result<Boolean>> = flow {
        try{
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(Result.Success(true))
        }catch (exception: Exception){
            emit(Result.Error(exception))
        }
    }
}
