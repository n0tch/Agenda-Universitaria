package com.core.data.repository.profile

import android.net.Uri
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.data.extension.toCurrentUser
import com.core.network.profile.ProfileDataProvider
import com.example.model.CurrentUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val profileDataProvider: ProfileDataProvider
) : ProfileRepository {
    override suspend fun updateRemoteUser(userName: String, photo: Uri?): CurrentUser {
        return profileDataProvider.updateProfile(userName, photo).toCurrentUser()
    }

    override suspend fun resetPassword(email: String): Flow<Boolean> = flow {
        profileDataProvider.resetPassword(email)
            .flowOn(ioDispatcher)
            .catch { throw it }
            .collect { emit(it) }
    }
}
