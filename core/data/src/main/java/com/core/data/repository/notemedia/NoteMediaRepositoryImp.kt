package com.core.data.repository.notemedia

import com.core.database.media.NoteMediaDao
import com.core.database.media.MediaEntity
import javax.inject.Inject

class NoteMediaRepositoryImp @Inject constructor(
    private val noteMediaDao: NoteMediaDao
): NoteMediaRepository {

    override suspend fun saveNoteMedias(noteId: Int, mediasUri: List<String>) {
        val medias = mediasUri.map { MediaEntity(noteId = noteId, uriPath = it) }
        noteMediaDao.saveNoteMedia(medias)
    }

    override suspend fun fetchNoteMediaByNoteId(noteId: Int): List<NoteMedia> {
        return noteMediaDao.fetchNoteMediaByNoteId(noteId).map { NoteMedia(it.noteId, it.uriPath) }
    }
}

data class NoteMedia(val noteId: Int, val uriPath: String)

fun NoteMedia.toEntity() = MediaEntity(
    noteId = noteId,
    uriPath = uriPath
)
