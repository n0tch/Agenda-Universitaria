package com.core.data.repository.notemedia

import android.net.Uri
import com.core.database.media.MediaEntity
import com.example.model.NoteMedia

fun NoteMedia.toEntity() = MediaEntity(
    noteId = noteId,
    uriPath = uriPath
)

fun List<Uri?>.toMediaEntity(noteId: Int) = mapNotNull { MediaEntity(noteId = noteId, uriPath = it.toString()) }
