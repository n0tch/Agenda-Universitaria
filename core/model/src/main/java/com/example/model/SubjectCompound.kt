package com.example.model

import java.time.DayOfWeek

data class SubjectCompound(
    val subject: Subject = Subject(),
    val timetables: Map<DayOfWeek, List<Timetable>> = emptyMap()
)

