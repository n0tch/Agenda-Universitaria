package com.core.network.model.timetableResponse

import com.core.network.model.BaseModel

data class TimetableResponse(
    override var id: String? = "",
    val weekDays: List<String?> = emptyList(),
    val startTime: String? = "",
    val endTime: String? = "",
    val subjectId: String? = "",
): BaseModel()
