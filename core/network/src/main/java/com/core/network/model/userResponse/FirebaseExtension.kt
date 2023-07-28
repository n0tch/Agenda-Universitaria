package com.core.network.model.userResponse

import com.google.firebase.auth.FirebaseUser

internal fun FirebaseUser?.toCurrentUserResponse() = CurrentUserResponse(
    id = this?.uid ?: "",
    email = this?.email ?: "",
    photoUrl = this?.photoUrl.toString(),
    userName = this?.displayName ?: ""
)