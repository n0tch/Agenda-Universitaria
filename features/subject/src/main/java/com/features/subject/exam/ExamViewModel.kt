package com.features.subject.exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Result
import com.core.domain.ExamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(
    private val examUseCase: ExamUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExamState1> by lazy { MutableStateFlow(ExamState1()) }
    val uiState: StateFlow<ExamState1> = _uiState.asStateFlow()

    fun fetchExams() {
        viewModelScope.launch {
            examUseCase
                .fetchAllExams()
                .onStart { _uiState.emit(ExamState1(loading = true)) }
                .collect {
                    when(it){
                        is Result.Error -> _uiState.emit(ExamState1(error = it.exception))
                        is Result.Success -> _uiState.emit(ExamState1(exams = it.data))
                    }
                }
        }
    }
}