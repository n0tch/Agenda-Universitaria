package com.example.model.event

data class EventSaveRequest(
    val event: String,
    val labelId: Int,
    val subjectId: Int,
    val score: Float?,
    val date: Long?,
    val hour: Int,
    val minute: Int
)
