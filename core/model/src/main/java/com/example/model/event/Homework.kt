package com.example.model.event

import com.example.model.ScheduledEvent
import java.time.LocalDateTime

data class Homework (
    override val id: String,
    override val subjectId: String,
    override val name: String,
    override val date: LocalDateTime,
    override val color: Int
): ScheduledEvent()