package com.home.home

import com.example.model.TimetableCompound
import com.example.model.event.Exam
import java.time.DayOfWeek

data class HomeTimetableState(
    val items: Map<DayOfWeek, List<TimetableCompound>> = mapOf(),
    val exception: Exception? = null
)

data class HomeDailyTimetableState(
    val items: List<TimetableCompound> = listOf(),
)

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

data class LogoutState(
    val logoutSuccess: Boolean = false,
    val exception: Exception? = null
)
