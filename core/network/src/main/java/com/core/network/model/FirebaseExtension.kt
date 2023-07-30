package com.core.network.model

import com.core.network.model.userResponse.CurrentUserResponse
import com.google.firebase.auth.FirebaseUser

internal fun FirebaseUser?.toCurrentUserResponse() = CurrentUserResponse(
    id = this?.uid ?: "",
    email = this?.email ?: "",
    photoUrl = this?.photoUrl.toString(),
    userName = this?.displayName ?: ""
)