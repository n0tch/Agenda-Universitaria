package com.home.home

import com.example.model.CurrentUser
import com.example.model.event.Exam

sealed class HomeState {
    class HomeCurrentUser(val currentUser: CurrentUser): HomeState()
    object HomeIdle: HomeState()
    class HomeCurrentUserError(val exception: Exception): HomeState()
    class HomeNextExams(val exams: List<Exam>): HomeState()
    class Error(val exception: Exception): HomeState()
}

data class CurrentUserState(
    val username: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val exception: Exception? = null
)

data class ExamsState(
    val nextExams: List<Exam> = emptyList(),
    val exception: Exception? = null
)

sealed class LogoutState: HomeState(){
    class LogoutError(val exception: Exception): LogoutState()
    class LogoutSuccess(val isLoggedOut: Boolean) : LogoutState()
}
