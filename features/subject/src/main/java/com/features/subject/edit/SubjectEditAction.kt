package com.features.subject.edit

import com.core.common.viewmodel.Action
import com.example.model.Subject
import com.example.model.Timetable
import com.example.model.event.NotificationEarlier

sealed class SubjectEditAction : Action {
    data class SaveSubject(
        val subject: Subject,
        val timetable: List<Timetable> = emptyList(),
        val notificationEnabled: Boolean = false,
        val notifyEarlier: NotificationEarlier = NotificationEarlier.DISABLED
    ) : SubjectEditAction()
}
