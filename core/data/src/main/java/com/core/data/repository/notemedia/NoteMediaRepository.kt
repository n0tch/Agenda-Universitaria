package com.core.data.repository.notemedia

import android.net.Uri
import com.example.model.NoteMedia

interface NoteMediaRepository {

    suspend fun saveNoteMedias(noteId: Int, mediasUri: List<Uri?>)

    suspend fun fetchNoteMediaByNoteId(noteId: Int): List<NoteMedia>

    suspend fun updateNoteMedia(noteId: Int, mediasUri: List<Uri?>)

}