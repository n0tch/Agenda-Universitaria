package com.core.database.databaseModel

import com.core.database.realmModel.WeekDayEnum

data class WeeklyTimetable(
    val timetable: MutableMap<WeekDayEnum, List<TimetableDatabaseModel>> = mutableMapOf()
)
