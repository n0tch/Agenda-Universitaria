package com.core.database.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.core.database.note.relations.NoteWithLabelWithMediaAndSubject

@Dao
interface NoteDao {

    @Insert
    suspend fun saveNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity): Int

    @Query("SELECT * FROM notes WHERE noteId = :id")
    suspend fun fetchNotesById(id: Int): NoteEntity

    @Query("SELECT * FROM notes")
    suspend fun fetchNotesBySubjectId(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%'")
    suspend fun searchNoteByQuery(query: String): List<NoteEntity>

    @Query("SELECT * FROM notes")
    suspend fun fetchCompoundNotes(): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM notes WHERE notes.noteId = :noteId")
    suspend fun fetchNoteWithLabelsAndSubject(noteId: Int): NoteWithLabelWithMediaAndSubject

    @Transaction
    @Query("SELECT * FROM notes")
    suspend fun fetchAllNoteCompound(): List<NoteWithLabelWithMediaAndSubject>
}