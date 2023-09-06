package com.example.model

import com.example.model.event.Exam

data class SubjectCompound(
    val subject: Subject?,
    val notes: List<Note>,
    val exams: List<Exam>
)
