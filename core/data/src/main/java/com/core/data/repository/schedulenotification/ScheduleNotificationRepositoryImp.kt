package com.core.data.repository.schedulenotification

import com.core.notification.AppNotificationManager
import com.example.model.NotificationDecorator
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import java.util.Calendar
import javax.inject.Inject

class ScheduleNotificationRepositoryImp @Inject constructor(
    private val notificationManager: AppNotificationManager
) : ScheduleNotificationRepository {

    override suspend fun scheduleAtExactTime(
        calendars: List<Calendar>,
        notificationDecorator: NotificationDecorator,
        notificationEarlier: NotificationEarlier,
        notificationRecurrence: NotificationPeriod
    ) {
        calendars.forEach { calendar ->
            notificationManager.scheduleNotification(
                calendar = calendar.apply {
                    val minute = calendar.get(Calendar.MINUTE) - notificationEarlier.min
                    set(Calendar.MINUTE, minute)
                },
                decorator = notificationDecorator,
                notificationPeriod = notificationRecurrence
            )
        }
    }

    override suspend fun notifyEarly(calendar: Calendar, minutes: Int) {
        TODO("Not yet implemented")
    }
}
