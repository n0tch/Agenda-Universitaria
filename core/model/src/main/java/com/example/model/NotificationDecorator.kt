package com.example.model

interface NotificationDecorator{
    fun selfId(): Int

    fun notificationTitle(): String

    fun notificationBody(): String
}
