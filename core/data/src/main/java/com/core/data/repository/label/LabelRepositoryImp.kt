package com.core.data.repository.label

import com.core.database.label.LabelDao
import com.example.model.Label
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class LabelRepositoryImp @Inject constructor(
    private val labelDao: LabelDao
): LabelRepository {
    override fun saveNoteLabel(label: Label): Flow<Label> = flow {
        labelDao.saveLabel(label.toEntity())
        emit(label)
    }

    override fun fetchNoteLabels(): Flow<List<Label>> = flow {
        val labels = labelDao.fetchAllLabels().map { it.toLabel() }
        emit(labels)
    }

    override suspend fun fetchNoteLabelByNoteId(noteId: Int): List<Label> {
        return emptyList()//labelDao.fetchLabelByNoteId(noteId).map { it.toLabel() }
    }
}