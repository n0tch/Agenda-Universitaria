package com.core.data.repository.schedulenotification

import com.example.model.NotificationDecorator
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import java.util.Calendar

interface ScheduleNotificationRepository {

    suspend fun scheduleAtExactTime(
        calendars: List<Calendar>,
        notificationDecorator: NotificationDecorator,
        notificationEarlier: NotificationEarlier,
        notificationRecurrence: NotificationPeriod
    )

    suspend fun notifyEarly(calendar: Calendar, minutes: Int)
}