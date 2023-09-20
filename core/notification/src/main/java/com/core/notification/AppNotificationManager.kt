package com.core.notification

import com.example.model.NotificationDecorator
import com.example.model.event.NotificationPeriod
import java.util.Calendar

interface AppNotificationManager {

    suspend fun scheduleNotification(
        calendar: Calendar,
        decorator: NotificationDecorator,
        notificationPeriod: NotificationPeriod
    ): Boolean

    suspend fun scheduleWeeklyNotification(dayOfWeek: Int, hour: Int, minute: Int): Boolean
}