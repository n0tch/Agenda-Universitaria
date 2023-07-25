package com.example.agendauniversitaria.network.login

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.UserCredentials
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseLoginNetwork @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseLoginDataSource {
    override fun loginWithCredentials(userCredentials: UserCredentials): Flow<Result<FirebaseUser?>> =
        callbackFlow {
            try {
                val user = firebaseAuth
                    .signInWithEmailAndPassword(userCredentials.email, userCredentials.password)
                    .await()
                    .user

                trySend(Result.Success(user))
            } catch (exception: Exception) {
                trySend(Result.Error(exception))
            }

            close()
            awaitClose()
        }

    override fun signUp(): Flow<Result<FirebaseUser?>> = flow {
        emit(Result.Error(Exception("Not implemented")))
    }

    override fun loginWithGoogle(): Flow<Result<FirebaseUser?>> = flow {
        emit(Result.Error(Exception("Not implemented")))
    }

    override fun loginWithFacebook(): Flow<Result<FirebaseUser?>> = flow {
        emit(Result.Error(Exception("Not implemented")))
    }

    override fun loginAnonymous(): Flow<Result<FirebaseUser?>> = callbackFlow {
        try {
            val user = firebaseAuth.signInAnonymously().await().user
            trySend(Result.Success(user))
        } catch (exception: Exception) {
            trySend(Result.Error(exception))
        }

        close()
        awaitClose()
    }

    override fun logout(): Flow<Result<Boolean>> = flow {
        try {
            firebaseAuth.signOut()
            emit(Result.Success(true))
        } catch (exception: Exception) {
            emit(Result.Error(exception))
        }
    }
}