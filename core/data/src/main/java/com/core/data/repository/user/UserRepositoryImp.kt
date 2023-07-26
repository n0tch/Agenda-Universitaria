package com.core.data.repository.user

import com.core.data.extension.toCurrentUser
import com.core.data.repository.user.UserRepository
import com.core.network.user.UserDataProvider
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDataProvider: UserDataProvider
) : UserRepository {

    override suspend fun fetchCurrentUser(): Flow<CurrentUser> = flow {
        userDataProvider
            .fetchCurrentUser()
            .catch { throw it }
            .map { it.toCurrentUser() }
            .collect{ emit(it) }
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun logout(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}