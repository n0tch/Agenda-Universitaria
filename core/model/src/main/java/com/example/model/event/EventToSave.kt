package com.example.model.event

import com.example.model.Label

data class EventToSave(
    val event: Event,
    val label: Label,
    val subjectId: Int,
    val hasScore: Boolean,
    val hasNotification: Boolean,
    val eventGroup: EventGroup? = null,
    val eventScore: EventScore? = null,
    val eventNotification: EventNotification? = null
)