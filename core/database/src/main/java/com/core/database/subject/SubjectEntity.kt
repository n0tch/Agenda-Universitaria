package com.core.database.subject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val subjectId: Int = 0,
    val name: String?,
    val place: String?,
    val teacher: String?,
)
