package com.example.model

data class TimetableCompound(
    val timetable: Timetable = Timetable(),
    val subject: Subject = Subject()
)
