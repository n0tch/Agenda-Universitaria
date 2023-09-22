package com.example.model.event

import com.example.model.Label
import com.example.model.Subject

data class EventCompound(
    val event: Event,
    val subject: Subject,
    val label: Label,
    val eventGroup: EventGroup? = null,
    val eventScore: EventScore? = null,
    val eventNotification: EventNotification? = null
)