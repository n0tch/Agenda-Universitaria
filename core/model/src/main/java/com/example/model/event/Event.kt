package com.example.model.event

data class Event(
    val id: Int = 0,
    val name: String,
    val description: String,
    val eventLabels: List<String>,
    val hasScore: Boolean,
    val isGroupEvent: Boolean,
    val notificationOn: Boolean,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)