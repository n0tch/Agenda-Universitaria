package com.features.subject.edit

import com.example.model.Subject
import com.example.model.Timetable
import com.example.model.event.NotificationEarlier

data class SubjectEditState(
    val subject: Subject = Subject(),
    val timetable: List<Timetable> = listOf(),
    var notificationEnabled: Boolean = false,
    var notificationEarlier: NotificationEarlier = NotificationEarlier.DISABLED
)

sealed class SubjectEditSideEffect {
    data class Toast(val message: String) : SubjectEditSideEffect()
    object SubjectSaved: SubjectEditSideEffect()
}