package com.core.notification.di

import com.core.notification.AppNotificationManager
import com.core.notification.NotificationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationModule {

    @Binds
    abstract fun bindNotificationManager(repo: NotificationManagerImpl): AppNotificationManager
}
