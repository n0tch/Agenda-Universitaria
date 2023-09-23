package com.example.model.event

import com.example.model.NotificationDecorator

data class Event(
    val id: Int = 0,
    val name: String,
    val date: Long,
    val color: String,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)

class EventNotificationDecorator(private val event: Event, private val labelName: String): NotificationDecorator{
    override fun selfId(): Int = event.id

    override fun notificationTitle(): String = "Lembrete de $labelName!"

    override fun notificationBody(): String = event.name

}
