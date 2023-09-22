package com.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.model.NotificationDecorator
import com.example.model.event.NotificationPeriod
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

internal class NotificationManagerImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val alarmManager: AlarmManager
) : AppNotificationManager {

    override suspend fun scheduleNotification(
        calendar: Calendar,
        decorator: NotificationDecorator,
        notificationPeriod: NotificationPeriod
    ): Boolean {
        val alarmIntent: PendingIntent = Intent(context, NotificationReceiver::class.java).let { intent ->
            intent.apply {
                putExtra("title", decorator.notificationTitle())
                putExtra("body", decorator.notificationBody())
            }
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }

//        when(notificationPeriod){
//            NotificationPeriod.DAILY -> alarmManager.setInexactRepeating(
//                AlarmManager.RTC_WAKEUP,
//                calendar.timeInMillis,
//                AlarmManager.INTERVAL_DAY,
//                alarmIntent
//            )
//            NotificationPeriod.WEEKLY ->
//                alarmManager.setInexactRepeating(
//                    AlarmManager.RTC_WAKEUP,
//                    calendar.timeInMillis,
//                    AlarmManager.INTERVAL_DAY * 7,
//                    alarmIntent
//                )
//            NotificationPeriod.ONCE ->
//                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
//        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
        Log.e("scheduled", calendar.toString())
        return true
    }

    override suspend fun scheduleWeeklyNotification(
        dayOfWeek: Int,
        hour: Int,
        minute: Int
    ): Boolean {

        val alarmIntent: PendingIntent = Intent(context, NotificationReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 1)
            set(Calendar.MILLISECOND, 0)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, 
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY * 7,
            alarmIntent
        )

        return true
    }
}
