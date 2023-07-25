package com.example.agendauniversitaria.domain.usecase

import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.network.user.UserDataSource
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val userDataSource: UserDataSource
) {

    suspend fun fetchCurrentUser(): Result<CurrentUser> =
        userDataSource.fetchCurrentUser()

    suspend fun saveUser(currentUser: CurrentUser): Result<Boolean> =
        userDataSource.saveUser(currentUser)

    //TODO: colocar num repository
    fun isUserLoggedIn(): Flow<Result<Boolean>> = flow {
        emit(Result.Success(Firebase.auth.currentUser != null))
    }

    fun removeUser() = userDataSource.logout()
}
