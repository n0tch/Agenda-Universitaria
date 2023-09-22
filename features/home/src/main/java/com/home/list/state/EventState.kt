package com.home.list.state

import com.example.model.event.EventCompound

data class EventState(
    val events: List<EventCompound> = emptyList()
)
