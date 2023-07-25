package com.core.network.model

import com.google.firebase.auth.FirebaseUser

fun FirebaseUser?.toCurrentUserResponse() = CurrentUserResponse(
    id = this?.uid ?: "",
    email = this?.email ?: "",
    photoUrl = this?.photoUrl.toString(),
    userName = this?.displayName ?: ""
)