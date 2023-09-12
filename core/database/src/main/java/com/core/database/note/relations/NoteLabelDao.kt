package com.core.database.note.relations

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteLabelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNoteLabel(noteLabels: List<NoteLabelCrossRef>): List<Long>

    @Query("DELETE FROM notelabelcrossref WHERE notelabelcrossref.noteId = :noteId")
    suspend fun deleteLabelByNoteId(noteId: Int)
}