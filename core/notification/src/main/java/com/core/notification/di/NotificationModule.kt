package com.core.notification.di

import android.app.AlarmManager
import android.content.Context
import com.core.notification.AppNotificationManager
import com.core.notification.NotificationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationModule {

    @Binds
    abstract fun bindNotificationManager(repo: NotificationManagerImpl): AppNotificationManager
}

@Module
@InstallIn(SingletonComponent::class)
internal class NotificationMod{

    @Provides
    fun providesAlarmManager(@ApplicationContext context: Context): AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
}