package com.features.subject.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.SubjectUseCase
import com.example.model.Subject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val subjectUseCase: SubjectUseCase
) : ViewModel() {

    init {
        fetchSubjects()
    }

    private val _uiState: MutableStateFlow<SubjectState> by lazy {
        MutableStateFlow(SubjectState())
    }

    val uiState: StateFlow<SubjectState> = _uiState.asStateFlow()

    fun saveSubject(subject: Subject) {
        viewModelScope.launch {
            subjectUseCase
                .saveSubject(subject)
                .flowOn(uiDispatcher)
                .onStart { _uiState.emit(SubjectState(isLoading = true)) }
                .catch { _uiState.emit(SubjectState(exception = it as Exception)) }
                .collect {
                    when (it) {
                        is Result.Error ->
                            _uiState.emit(SubjectState(exception = it.exception))
                        is Result.Success ->
                            _uiState.emit(SubjectState(subjectId = it.data.id))
                    }
                }
        }
    }

    fun fetchSubjects(){
        viewModelScope.launch {
            subjectUseCase
                .fetchSubjects()
                .flowOn(uiDispatcher)
                .catch { _uiState.emit(SubjectState(exception = it as Exception)) }
                .collect {
                    when(it){
                        is Result.Error ->
                            _uiState.emit(SubjectState(exception = it.exception))
                        is Result.Success ->
                            _uiState.emit(SubjectState(subjects = it.data))
                    }
                }
        }
    }
}