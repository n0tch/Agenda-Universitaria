package com.core.domain

import com.core.common.Result
import com.core.data.repository.label.LabelRepository
import com.example.model.Label
import javax.inject.Inject

class LabelUseCase @Inject constructor(
    private val labelRepository: LabelRepository
) {

    suspend fun saveLabel(label: Label): Result<Label> = try {
        val savedLabel = labelRepository.saveNoteLabel(label)
        Result.Success(savedLabel)
    }catch (exception: Exception){
        Result.Error(exception)
    }

    suspend fun fetchNoteLabels(): Result<List<Label>> = try {
        val labels = labelRepository.fetchNoteLabels()
        Result.Success(labels)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
