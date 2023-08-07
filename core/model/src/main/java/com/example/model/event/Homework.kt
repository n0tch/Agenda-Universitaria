package com.example.model.event

import com.example.model.ScheduledEvent

data class Homework (
    override val id: String,
    override val subjectId: String,
    override val name: String,
    override val date: Long,
    override val color: Int
): ScheduledEvent()