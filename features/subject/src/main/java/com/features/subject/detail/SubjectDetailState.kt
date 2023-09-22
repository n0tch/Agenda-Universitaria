package com.features.subject.detail

import com.example.model.NoteWithLabelCompound
import com.example.model.SubjectCompound
import com.example.model.Timetable
import com.example.model.event.EventCompound
import java.time.DayOfWeek

data class SubjectDetailState(
    val isLoading: Boolean = false,
    val subjectCompound: SubjectCompound = SubjectCompound(),
    val events: List<EventCompound> = emptyList(),
    val notesWithLabelCompound: List<NoteWithLabelCompound> = emptyList(),
    val exception: Exception? = null
)

sealed class SubjectDetailSideEffect {
    data class Toast(val message: String) : SubjectDetailSideEffect()
}