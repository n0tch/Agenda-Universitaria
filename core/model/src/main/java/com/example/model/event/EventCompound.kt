package com.example.model.event

data class EventCompound(
    val event: Event,
    val eventGroup: EventGroup? = null,
    val eventScore: EventScore? = null,
    val eventNotification: EventNotification? = null
)