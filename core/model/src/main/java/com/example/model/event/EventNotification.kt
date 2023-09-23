package com.example.model.event

data class EventNotification(
    val id: Int = 0,
    val eventId: Int = 0,
    val notifyAt: Long,
    val enabled: Boolean = true,
    val notificationEarly: NotificationEarlier = NotificationEarlier.IN_TIME,
    val notificationPeriod: NotificationPeriod = NotificationPeriod.ONCE,
)
