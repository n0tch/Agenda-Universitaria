package com.core.data.repository.note

import com.core.database.note.relations.NoteLabelCrossRef
import com.core.database.note.relations.NoteLabelDao
import javax.inject.Inject

class NoteLabelRepositoryImp @Inject constructor(
    private val noteLabelDao: NoteLabelDao
): NoteLabelRepository {

    override suspend fun saveNoteLabels(noteId: Int, labelIds: List<Int>) {
        val noteLabels = labelIds.map { NoteLabelCrossRef(noteId, it) }
        noteLabelDao.saveNoteLabel(noteLabels)
    }
}