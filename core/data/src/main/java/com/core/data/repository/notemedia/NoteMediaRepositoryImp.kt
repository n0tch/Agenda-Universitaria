package com.core.data.repository.notemedia

import android.net.Uri
import android.util.Log
import com.core.database.media.NoteMediaDao
import com.core.database.note.relations.NoteMediaCrossRef
import com.example.model.NoteMedia
import javax.inject.Inject

class NoteMediaRepositoryImp @Inject constructor(
    private val noteMediaDao: NoteMediaDao
): NoteMediaRepository {

    override suspend fun saveNoteMedias(noteId: Int, mediasUri: List<Uri?>) {
        val ids = noteMediaDao.saveNoteMedia(mediasUri.toMediaEntity(noteId))
        val mediaRefs = ids.map { NoteMediaCrossRef(noteId, it.toInt()) }
        noteMediaDao.saveNoteAndMedia(mediaRefs)
        Log.e("save nome medias", ids.toString())
    }

    override suspend fun fetchNoteMediaByNoteId(noteId: Int): List<NoteMedia> {
        return noteMediaDao.fetchNoteMediaByNoteId(noteId).map { NoteMedia(it.noteId, it.uriPath) }
    }

    override suspend fun updateNoteMedia(noteId: Int, mediasUri: List<Uri?>) {
        noteMediaDao.deleteNoteAndMediaByNoteIds(noteId)
        noteMediaDao.saveNoteMedia(mediasUri.toMediaEntity(noteId))
    }
}
