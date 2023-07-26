package com.home.home

import com.example.model.CurrentUser

sealed class HomeState {
    class HomeCurrentUser(val currentUser: CurrentUser): HomeState()
    object HomeIdle: HomeState()
    class HomeCurrentUserError(val exception: Exception): HomeState()
}

sealed class LogoutState: HomeState(){
    class LogoutError(val exception: Exception): LogoutState()
    class LogoutSuccess(val isLoggedOut: Boolean) : LogoutState()
}
