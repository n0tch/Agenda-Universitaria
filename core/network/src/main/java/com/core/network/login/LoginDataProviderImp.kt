package com.core.network.login

import com.core.network.model.userResponse.CurrentUserResponse
import com.core.network.model.userResponse.toCurrentUserResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class LoginDataProviderImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): LoginDataProvider {

    override fun loginWithCredentials(
        email: String,
        password: String
    ): Flow<CurrentUserResponse> = flow {
        val user = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user

        emit(user.toCurrentUserResponse())
    }

    override fun loginWithGoogle(): Flow<CurrentUserResponse> = flow {
        throw Exception("Not implemented")
    }

    override fun loginWithFacebook(): Flow<CurrentUserResponse> = flow {
        throw Exception("Not implemented")
    }

    override fun loginAnonymous(): Flow<CurrentUserResponse> = flow {
        throw Exception("Not implemented")
    }

    override fun logout(): Flow<Boolean> = flow {
        firebaseAuth.signOut()
        emit(true)
    }

}