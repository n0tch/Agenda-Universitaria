package com.example.agendauniversitaria.network.user

import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.localstorage.UserDataStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDataSourceImp @Inject constructor(
    private val userStorage: UserDataStorage,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : UserDataSource {

    override suspend fun fetchCurrentUser(): Result<CurrentUser> = try {
        val currentUser = userStorage.getCurrentUser()
        Result.Success(currentUser)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    override suspend fun saveUser(user: CurrentUser): Result<Boolean> = userStorage.insert(user)

    override fun isUserLoggedIn(): Flow<Result<Boolean>> = flow {
        userStorage.isLogged().flowOn(ioDispatcher)
            .catch {
                emit(Result.Error(it as Exception))
            }.collect {
                emit(Result.Success(it))
            }
    }

    override fun logout(): Flow<Result<Boolean>> = flow {
        userStorage.removeUser().flowOn(ioDispatcher).catch { emit(Result.Error(it as Exception)) }
            .collect {
                emit(Result.Success(it))
            }
    }
}
