package com.core.database.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.core.database.note.relations.NoteWithLabel
import com.core.database.note.relations.NoteWithLabelWithMediaAndSubject

@Dao
interface NoteDao {

    @Insert
    suspend fun saveNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity): Int

    @Transaction
    @Query("SELECT * FROM notes WHERE notes.noteSubjectId = :subjectId")
    suspend fun fetchNotesBySubjectId(subjectId: Int): List<NoteWithLabel>

    @Transaction
    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%'")
    suspend fun searchNoteByQuery(query: String): List<NoteWithLabelWithMediaAndSubject>

    @Transaction
    @Query("SELECT * FROM notes ORDER BY coalesce(notes.updatedAt, notes.createdAt) DESC")
    suspend fun fetchCompoundNotes(): List<NoteWithLabelWithMediaAndSubject>

    @Transaction
    @Query("SELECT * FROM notes ORDER BY coalesce(notes.updatedAt, notes.createdAt) DESC LIMIT :count")
    suspend fun fetchCompoundNotes(count: Int): List<NoteWithLabelWithMediaAndSubject>

    @Transaction
    @Query("SELECT * FROM notes WHERE notes.noteId = :noteId")
    suspend fun fetchNoteWithLabelsAndSubject(noteId: Int): NoteWithLabelWithMediaAndSubject

    @Transaction
    @Query("""SELECT * FROM notes 
                WHERE notes.noteId in 
                (SELECT noteId FROM notelabelcrossref WHERE notelabelcrossref.labelId = :labelId)
            """)
    suspend fun fetchNotesByLabelId(labelId: Int): List<NoteWithLabelWithMediaAndSubject>

    @Query("SELECT COUNT(*) FROM notes")
    fun fetchNoteCount(): Int
}
