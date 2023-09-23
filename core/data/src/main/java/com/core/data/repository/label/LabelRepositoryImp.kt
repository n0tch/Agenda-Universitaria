package com.core.data.repository.label

import com.core.database.label.LabelDao
import com.example.model.Label
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class LabelRepositoryImp @Inject constructor(
    private val labelDao: LabelDao
): LabelRepository {

    override suspend fun saveNoteLabel(label: Label) : Label {
        val id = labelDao.saveLabel(label.toEntity())
        return label.copy(id = id.toInt())
    }

    override suspend fun fetchNoteLabels(): List<Label> {
        return labelDao.fetchAllLabels().map { it.toLabel() }
    }

    override suspend fun fetchNoteLabelByNoteId(noteId: Int): List<Label> {
        return emptyList()//labelDao.fetchLabelByNoteId(noteId).map { it.toLabel() }
    }
}
