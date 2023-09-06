package com.core.database.note.relations

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NoteLabelDao {

    @Insert
    suspend fun saveNoteLabel(noteLabels: List<NoteLabelCrossRef>): List<Long>
}