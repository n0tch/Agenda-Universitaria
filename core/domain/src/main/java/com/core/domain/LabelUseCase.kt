package com.core.domain

import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.label.LabelRepository
import com.example.model.Label
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LabelUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val labelRepository: LabelRepository
) {

    fun saveNoteLabel(label: Label): Flow<Result<Label>> = flow {
        labelRepository
            .saveNoteLabel(label)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .collect {
                emit(Result.Success(it))
            }
    }

    suspend fun fetchNoteLabels(): Result<List<Label>> = try {
        val labels = labelRepository.fetchNoteLabels()
        Result.Success(labels)
    }catch (exception: Exception){
        Result.Error(exception)
    }

}