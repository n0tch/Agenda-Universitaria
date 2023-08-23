package com.feature.timetable

import com.example.model.TimetableEntry

sealed class TimetableState {
    object Idle: TimetableState()
    class Entries(val items: List<TimetableEntry>) : TimetableState()
}
