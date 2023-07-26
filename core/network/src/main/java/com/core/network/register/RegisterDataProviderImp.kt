package com.core.network.register

import com.core.network.model.CurrentUserResponse
import com.core.network.model.toCurrentUserResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class RegisterDataProviderImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): RegisterDataProvider {

    override suspend fun signUpWithCredentials(email: String, password: String): CurrentUserResponse = try {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
            .user
            .toCurrentUserResponse()
    } catch (exception: Exception){
        throw exception
    }
}