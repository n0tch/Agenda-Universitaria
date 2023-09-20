package com.example.model

import com.example.model.event.NotificationEarlier

data class SubjectNotification(
    val id: Int,
    val notificationAt: Long,
    val timeEarlier: NotificationEarlier
)
