package com.example.model

abstract class ScheduledEvent{
    abstract val id: Int?
    abstract val subjectId: Int
    abstract val name: String
    abstract val date: Long
}
