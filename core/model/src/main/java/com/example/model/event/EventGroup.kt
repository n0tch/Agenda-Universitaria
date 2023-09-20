package com.example.model.event

data class EventGroup(
    val id: Int,
    val eventId: Int,
    val personsId: List<Int>
)