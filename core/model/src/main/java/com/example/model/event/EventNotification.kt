package com.example.model.event

data class EventNotification(
    val id: Int = 0,
    val eventId: Int = 0,
    val notifyAt: Long,
    val notificationEarly: NotificationEarlier,
    val notificationPeriod: NotificationPeriod,
)