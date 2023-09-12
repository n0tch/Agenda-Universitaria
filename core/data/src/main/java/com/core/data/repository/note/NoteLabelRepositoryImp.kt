package com.core.data.repository.note

import com.core.database.note.relations.NoteLabelCrossRef
import com.core.database.note.relations.NoteLabelDao
import com.example.model.Label
import javax.inject.Inject

class NoteLabelRepositoryImp @Inject constructor(
    private val noteLabelDao: NoteLabelDao
): NoteLabelRepository {

    override suspend fun saveNoteLabels(noteId: Int, labelIds: List<Label>) {
        val noteLabels = labelIds.map { NoteLabelCrossRef(noteId, it.id) }
        noteLabelDao.saveNoteLabel(noteLabels)
    }

    override suspend fun updateNoteLabels(noteId: Int, labelId: List<Label>) {
        noteLabelDao.deleteLabelByNoteId(noteId)
        noteLabelDao.saveNoteLabel(labelId.map { NoteLabelCrossRef(noteId, it.id) })
    }
}