package com.core.database.label

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.core.database.label.relations.LabelWithNotes

@Dao
interface LabelDao {
    @Insert
    suspend fun saveLabel(labelEntity: LabelEntity): Long

    @Delete
    suspend fun deleteLabel(labelEntity: LabelEntity)

    @Update
    suspend fun updateLabel(labelEntity: LabelEntity)

    @Query("SELECT * FROM labels")
    suspend fun fetchAllLabels(): List<LabelEntity>

    @Query("SELECT * FROM labels WHERE labels.labelId = :labelId")
    suspend fun fetchLabelById(labelId: Int): LabelEntity

//    @Query("SELECT * FROM labels WHERE labels.noteId = :noteId")
//    suspend fun fetchLabelByNoteId(noteId: Int): List<LabelEntity>

    @Transaction
    @Query("SELECT * FROM labels WHERE labels.labelId = :labelId")
    suspend fun fetchLabelWithNote(labelId: Int): LabelWithNotes
}