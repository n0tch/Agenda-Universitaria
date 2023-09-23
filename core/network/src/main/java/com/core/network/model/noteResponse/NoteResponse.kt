package com.core.network.model.noteResponse

import com.core.network.model.BaseModel

data class NoteResponse(
    override var id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val label: String? = null,
    val subject: String? = null
): BaseModel()
