package com.example.agendauniversitaria.feature.home

import com.example.agendauniversitaria.domain.model.CurrentUser

sealed class HomeState {
    class HomeCurrentUser(val currentUser: CurrentUser): HomeState()
    object HomeIdle: HomeState()
    class HomeCurrentUserError(val exception: Exception): HomeState()
}

sealed class LogoutState: HomeState(){
    class LogoutError(val exception: Exception): LogoutState()
    class LogoutSuccess(val local: Boolean, val remote: Boolean) : LogoutState()
}
