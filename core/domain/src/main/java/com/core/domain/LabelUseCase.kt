package com.core.domain

import android.util.Log
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
import kotlinx.coroutines.flow.map
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

    fun fetchNoteLabels(): Flow<Result<List<Label>>> = flow {
        labelRepository
            .fetchNoteLabels()
            .flowOn(ioDispatcher)
            .catch {
                Log.e("fetchNoteLabels", "${it.message}")
                emit(Result.Error(it as Exception))
            }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

}