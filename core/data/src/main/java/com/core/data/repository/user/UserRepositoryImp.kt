package com.core.data.repository.user

import com.core.data.extension.toCurrentUser
import com.core.network.user.UserDataProvider
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UserRepositoryImp @Inject constructor(
    private val userDataProvider: UserDataProvider
) : UserRepository {

    override suspend fun fetchCurrentUser(): CurrentUser {
        return userDataProvider.fetchCurrentUser().toCurrentUser()
    }

    override fun isUserLoggedIn(): Flow<Boolean> = flow {
        userDataProvider.isUserLoggedIn().collect { emit(it) }
    }

    override fun logout(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}
