package com.example.agendauniversitaria.network.model

import com.example.agendauniversitaria.domain.model.CurrentUser
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser?.toCurrentUserModel(photoUrl: String = "") = CurrentUser(
    id = this?.uid ?: "",
    email = this?.email ?: "",
    photoUrl = this?.photoUrl.toString(),
    userName = this?.displayName ?: ""
)