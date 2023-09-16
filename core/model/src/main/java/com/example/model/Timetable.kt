package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timetable(
    val id: Int = 0,
    val weekDay: String = "",
    var startTime: Long = 0,
    var endTime: Long = 0,
    var subjectId: Int = 0
) : Parcelable