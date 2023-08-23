package com.example.model

import java.time.LocalDateTime

abstract class ScheduledEvent{
    abstract val id: String
    abstract val subjectId: String
    abstract val name: String
    abstract val date: LocalDateTime
    abstract val color: Int
}
