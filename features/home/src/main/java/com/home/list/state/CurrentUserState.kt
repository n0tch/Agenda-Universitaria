package com.home.list.state

data class CurrentUserState(
    val username: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val exception: Exception? = null
)