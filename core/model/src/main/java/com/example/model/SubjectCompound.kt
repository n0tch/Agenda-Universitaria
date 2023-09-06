package com.example.model

import com.example.model.event.Exam

data class SubjectCompound(
    val subject: Subject = Subject(),
    val notes: List<Note> = emptyList(),
    val exams: List<Exam> = emptyList()
)
