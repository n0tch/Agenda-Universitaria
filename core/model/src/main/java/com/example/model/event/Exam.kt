package com.example.model.event

import com.example.model.ScheduledEvent

class Exam(
    val relatedNotes: List<String>,
    val score: Float,
    override val id: String,
    override val subjectId: String,
    override val name: String,
    override val date: Long,
    override val color: Int = 1
) : ScheduledEvent() {
    companion object{
        fun getMock() = Exam(
            relatedNotes = listOf(),
            score = 1.2F,
            id = "",
            subjectId = "",
            name = "",
            date = 1L,
            color = 1
        )
    }
}
