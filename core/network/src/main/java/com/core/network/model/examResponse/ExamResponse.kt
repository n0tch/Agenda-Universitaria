package com.core.network.model.examResponse

import com.core.network.model.BaseModel

data class ExamResponse(
    override var id: String? = "",
    val name: String? = "",
    val subjectId: String? = "",
    val relatedNotes: List<String>? = emptyList(),
    val score: Float? = 0F,
    val date: Long? = 0L
): BaseModel()
