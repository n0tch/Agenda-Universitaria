package com.example.agendauniversitaria.localstorage

import android.content.Context
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserStorageDataSourceImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : UserDataStorage {

    private val preferences by lazy {
        context.getSharedPreferences(
            USER_PREFERENCE_KEY,
            Context.MODE_PRIVATE
        )
    }

    override suspend fun insert(data: CurrentUser): Result<Boolean> = try {
        val currentUserStringify = gson.toJson(data)
        preferences
            .edit()
            .putString(USER_KEY, currentUserStringify)
            .putBoolean(USER_LOGGED_KEY, true)
            .apply()
        Result.Success(true)
    }catch (exception: Exception){
        Result.Error(exception)
    }

    override fun get(): Flow<CurrentUser> = flow {
        val storedUser = preferences.getString(USER_KEY, EMPTY_KEY)

        val user = gson
            .fromJson<CurrentUser>(
                storedUser,
                object : TypeToken<CurrentUser>() {}.type
            )
        emit(user)
    }

    override fun isLogged(): Flow<Boolean>  = flow {
        val isUserLogged = preferences.getBoolean(USER_LOGGED_KEY, false)
        emit(isUserLogged)
    }

    override suspend fun getCurrentUser(): CurrentUser = try{
        val storedUser = preferences.getString(USER_KEY, EMPTY_KEY)

        gson
            .fromJson(
                storedUser,
                object : TypeToken<CurrentUser>() {}.type
            )
    }catch (exception: Exception){
        throw exception
    }

    override fun removeUser(): Flow<Boolean> = flow {
        preferences.edit().putBoolean(USER_LOGGED_KEY, false).apply()
        emit(true)
    }

    companion object {
        private const val USER_PREFERENCE_KEY = "USER_PREFERENCE_KEY"
        private const val USER_KEY = "USER_KEY"
        private const val USER_LOGGED_KEY = "USER_LOGGED_KEY"
        private const val EMPTY_KEY = ""
    }
}