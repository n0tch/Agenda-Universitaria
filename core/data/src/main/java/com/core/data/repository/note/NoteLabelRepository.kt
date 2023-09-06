package com.core.data.repository.note

interface NoteLabelRepository {

    suspend fun saveNoteLabels(noteId: Int, labelId: List<Int>)

}