package com.core.data.repository.user

import com.core.data.extension.toCurrentUser
import com.core.data.repository.user.UserRepository
import com.core.network.user.UserDataProvider
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
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