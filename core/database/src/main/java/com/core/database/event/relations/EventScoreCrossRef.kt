package com.core.database.event.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["eventId", "scoreId"])
data class EventScoreCrossRef(
    @ColumnInfo(index = true)
    val eventId: Int,
    @ColumnInfo(index = true)
    val labelId: Int
)
