package com.example.model

data class Note(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val subjectId: Int = 0,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)
