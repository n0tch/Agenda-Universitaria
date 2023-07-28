package com.core.data.extension

import com.core.network.model.userResponse.CurrentUserResponse
import com.example.model.CurrentUser

fun CurrentUserResponse.toCurrentUser() = CurrentUser(
    id = this.id ?: "",
    email= this.email ?: "",
    photoUrl= this.photoUrl ?: "",
    userName= this.userName ?: ""
)