package com.core.network.model.subjectResponse

import com.core.network.model.BaseModel

data class SubjectModel(
    val name: String? = "",
    val placeName: String? = "",
    val teacherName: String? = ""
): BaseModel()
