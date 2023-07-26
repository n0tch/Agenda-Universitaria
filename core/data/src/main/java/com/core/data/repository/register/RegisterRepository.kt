package com.core.data.repository.register

import android.net.Uri
import com.core.common.Result
import com.core.network.model.CurrentUserResponse

interface RegisterRepository {

    suspend fun signupWithUserCredentials(
        email: String,
        password: String,
        photoUri: Uri?
    ): Result<CurrentUserResponse>

}
