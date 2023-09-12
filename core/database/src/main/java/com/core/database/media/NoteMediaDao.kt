package com.core.database.media

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.core.database.note.relations.NoteMediaCrossRef

@Dao
interface NoteMediaDao {

    @Insert
    suspend fun saveNoteMedia(noteMediaEntities: List<MediaEntity>): List<Long>

    @Insert
    suspend fun saveNoteAndMedia(noteMediaCrossRefs: List<NoteMediaCrossRef>)

    @Query("DELETE FROM NoteMediaCrossRef WHERE NoteMediaCrossRef.noteId = :noteId")
    suspend fun deleteNoteAndMediaByNoteIds(noteId: Int): Int

    @Query("SELECT * FROM note_media WHERE note_media.noteId = :noteId")
    suspend fun fetchNoteMediaByNoteId(noteId: Int): List<MediaEntity>
}
