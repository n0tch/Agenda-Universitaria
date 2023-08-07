package com.features.subject.exam.detail

import com.example.model.event.Exam
import com.example.model.Subject
import com.example.model.state.BaseState

data class ExamDetailState(
    val subjects: List<Subject> = emptyList(),
    val saved: Boolean = false,
    override val loading: Boolean = false,
    override val error: Exception? = null,
): BaseState

data class ExamState(
    var id: String = "",
    var title: String = "",
    var subject: String = "",
    val relatedNotes: MutableList<String> = mutableListOf(),
    var date: String = "",
    var score: String = ""
)

fun ExamState.toModel() = Exam(
    id = "",
    subjectId = subject,
    name = title,
    date = date.toLong(),
    relatedNotes = relatedNotes,
    score = score.toFloat()
)