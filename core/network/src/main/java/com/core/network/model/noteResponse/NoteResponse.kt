package com.core.network.model.noteResponse

data class NoteResponse(
    var id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val label: String? = null,
    val subject: String? = null
)