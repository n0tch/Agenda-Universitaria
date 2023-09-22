package com.features.event.list

import com.example.model.event.EventCompound

data class ListEventState(
    val events: List<EventCompound> = emptyList()
)

sealed class ListEventSideEffect{

}