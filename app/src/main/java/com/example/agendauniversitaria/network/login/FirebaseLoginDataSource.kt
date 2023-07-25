package com.example.agendauniversitaria.network.login

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.UserCredentials
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseLoginDataSource {

    fun loginWithCredentials(userCredentials: UserCredentials): Flow<Result<FirebaseUser?>>

    fun signUp(): Flow<Result<FirebaseUser?>>

    fun loginWithGoogle(): Flow<Result<FirebaseUser?>>

    fun loginWithFacebook(): Flow<Result<FirebaseUser?>>

    fun loginAnonymous(): Flow<Result<FirebaseUser?>>

    fun logout(): Flow<Result<Boolean>>
}
