package com.core.database.label

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labels")
data class LabelEntity(
    @PrimaryKey(autoGenerate = true) val labelId: Int = 0,
    val name: String?
)