package com.feature.exam.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.core.common.Result
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.domain.ExamUseCase
import com.core.domain.SubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamDetailViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val subjectUseCase: SubjectUseCase,
    private val examUseCase: ExamUseCase
) : ViewModel() {

    init {
        fetchSubjects()
    }

    private val _uiState: MutableStateFlow<ExamDetailState> by lazy { MutableStateFlow(
        ExamDetailState()
    ) }

    val uiState: StateFlow<ExamDetailState> = _uiState
    val examState: MutableStateFlow<ExamState> by lazy { MutableStateFlow(ExamState()) }

    @VisibleForTesting
    private fun fetchSubjects() {
        viewModelScope.launch {
            subjectUseCase
                .getSubjects()
                .flowOn(uiDispatcher)
//                .onStart { _uiState.emit(ExamDetailState(loading = true)) }
                .collect {
                    when (it) {
                        is Result.Error -> _uiState.emit(ExamDetailState(error = it.exception))
                        is Result.Success -> {
                            _uiState.emit(ExamDetailState(subjects = it.data))
                        }
                    }
                }
        }
    }

    fun saveExam() {
        viewModelScope.launch {
            examUseCase
                .saveExam(examState.value.toModel())
                .onStart { _uiState.emit(ExamDetailState(loading = true)) }
                .collect {
                    when (it) {
                        is Result.Error -> _uiState.emit(ExamDetailState(error = it.exception))
                        is Result.Success -> _uiState.emit(ExamDetailState(saved = true))
                    }
                }
        }
    }
}