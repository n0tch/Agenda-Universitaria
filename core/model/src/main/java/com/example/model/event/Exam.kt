package com.example.model.event

import com.example.model.ScheduledEvent
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Exam(
    val relatedNotes: List<String>,
    val score: Float,
    override val id: Int = 0,
    override val subjectId: Int,
    override val name: String,
    override val date: Long,
) : ScheduledEvent() {

    companion object {
        fun getMock() = Exam(
            relatedNotes = listOf(),
            score = 1.2F,
            id = 1,
            subjectId = 1,
            name = "",
            date = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        )
    }
}
