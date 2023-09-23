package com.core.domain

import com.core.common.Result
import com.core.data.repository.user.UserRepository
import com.example.model.CurrentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun fetchCurrentUser(): Result<CurrentUser> = try {
        val user = userRepository.fetchCurrentUser()
        Result.Success(user)
    }catch (exception: Exception){
        Result.Error(exception)
    }

    fun isUserLoggedIn(): Flow<Result<Boolean>> = flow {
        userRepository.isUserLoggedIn().map { Result.Success(it) }.collect { emit(it) }
    }
}
