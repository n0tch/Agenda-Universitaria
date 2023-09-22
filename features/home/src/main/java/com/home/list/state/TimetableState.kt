package com.home.list.state

import com.example.model.TimetableCompound
import java.time.DayOfWeek
import java.time.LocalDate

data class TimetableState(
    val selectedDayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek,
    val timetables: List<TimetableCompound> = emptyList()
)
