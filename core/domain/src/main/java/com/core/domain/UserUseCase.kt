package com.core.domain

import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.user.UserRepository
import com.example.model.CurrentUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) {

    suspend fun fetchCurrentUser(): Flow<Result<CurrentUser>> = flow {
        val user = userRepository.fetchCurrentUser()
        emit(Result.Success(user))
    }.flowOn(ioDispatcher)

    fun isUserLoggedIn(): Flow<Result<Boolean>> {
        TODO("")
    }

    fun logout(): Flow<Result<Boolean>> {
        TODO("")
    }
}