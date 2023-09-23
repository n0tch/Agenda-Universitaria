package com.core.network.register

import com.core.network.model.userResponse.CurrentUserResponse
import com.core.network.model.toCurrentUserResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class RegisterDataProviderImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): RegisterDataProvider {

    override suspend fun signUpWithCredentials(email: String, password: String): CurrentUserResponse {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
            .user
            .toCurrentUserResponse()
    }
}
