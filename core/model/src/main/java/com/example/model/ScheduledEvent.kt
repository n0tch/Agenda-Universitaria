package com.example.model

abstract class ScheduledEvent{
    abstract val id: String
    abstract val subjectId: String
    abstract val name: String
    abstract val date: Long
    abstract val color: Int
}
