package com.core.network.user

import com.core.network.model.userResponse.CurrentUserResponse
import com.core.network.model.userResponse.toCurrentUserResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataProviderImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : UserDataProvider {
    override suspend fun fetchCurrentUser(): CurrentUserResponse {
        return firebaseAuth.currentUser.toCurrentUserResponse()
    }

    override fun isUserLoggedIn(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }

    override fun logout(): Flow<Boolean> = flow {
        emit(firebaseAuth.signOut().run { true })
    }
}