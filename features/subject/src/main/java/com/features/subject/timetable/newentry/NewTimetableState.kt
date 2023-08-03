package com.features.subject.timetable.newentry

import com.example.model.Subject

sealed class NewTimetableState {
    object Idle: NewTimetableState()
    class Subjects(val subjects: List<Subject>): NewTimetableState()
}
