package com.core.notification

interface AppNotificationManager {

    suspend fun scheduleNotification(dayOfWeek: Int, hour: Int, minute: Int): Boolean

}