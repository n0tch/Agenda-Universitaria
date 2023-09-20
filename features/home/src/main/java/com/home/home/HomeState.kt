package com.home.home

import com.example.model.Note
import com.example.model.NoteCompound
import com.example.model.Subject
import com.example.model.TimetableCompound
import com.example.model.event.Exam
import com.core.common.viewmodel.Action
import java.time.DayOfWeek
import java.time.LocalDate

data class CurrentUserState(
    val username: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val exception: Exception? = null
)

data class NoteState(
    val notes: List<NoteCompound> = emptyList(),
    val totalCount: Int = 0,
    val exception: Exception? = null
)

data class ExamsState(
    val nextExams: List<Exam> = emptyList(),
    val totalCount: Int = 0,
    val exception: Exception? = null
)

data class SubjectConfigState(
    val showDialog: Boolean = false,
    val subject: Subject = Subject()
)

data class TimetableState(
    val selectedDayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek,
    val timetables: List<TimetableCompound> = emptyList()
)

sealed class HomeActon : Action {
    data class ExamSelected(val exam: Exam): HomeActon()

    data class DaySelected(val dayOfWeek: DayOfWeek): HomeActon()

    data class TimeTableConfig(val subject: Subject): HomeActon()

    data class NoteClicked(val note: Note): HomeActon()
}