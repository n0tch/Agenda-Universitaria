package com.core.data.repository.notemedia

interface NoteMediaRepository {

    suspend fun saveNoteMedias(noteId: Int, mediasUri: List<String>)

    suspend fun fetchNoteMediaByNoteId(noteId: Int): List<NoteMedia>

}