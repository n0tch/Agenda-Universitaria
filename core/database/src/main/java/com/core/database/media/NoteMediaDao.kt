package com.core.database.media

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteMediaDao {

    @Insert
    suspend fun saveNoteMedia(noteMediaEntities: List<MediaEntity>): List<Long>

    @Query("SELECT * FROM note_media WHERE note_media.noteId = :noteId")
    suspend fun fetchNoteMediaByNoteId(noteId: Int): List<MediaEntity>
}
