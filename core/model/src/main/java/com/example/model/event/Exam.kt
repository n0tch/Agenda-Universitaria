package com.example.model.event

import com.example.model.ScheduledEvent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Exam(
    val relatedNotes: List<String>,
    val score: Float,
    override val id: String,
    override val subjectId: String,
    override val name: String,
    override val date: LocalDateTime,
    override val color: Int = 1
) : ScheduledEvent() {

    fun getFormattedDate(): String = DateTimeFormatter
        .ofPattern("dd/MM/yyyy")
        .format(date)

    companion object {
        fun getMock() = Exam(
            relatedNotes = listOf(),
            score = 1.2F,
            id = "",
            subjectId = "",
            name = "",
            date = LocalDateTime.now(),
            color = 1
        )
    }
}
