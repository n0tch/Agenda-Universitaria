package com.features.subject.detail

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val subjectUseCase: SubjectUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<SubjectDetailState> by lazy { MutableStateFlow(SubjectDetailState()) }
    val uiState: StateFlow<SubjectDetailState> = _uiState.asStateFlow()

    fun fetchSubject(subjectId: Int){
        viewModelScope.launch {
            subjectUseCase
                .fetchSubject(subjectId)
                .flowOn(uiDispatcher)
                .collect {
                    when(it){
                        is Result.Error -> _uiState.emit(SubjectDetailState(exception = it.exception))
                        is Result.Success -> _uiState.emit(SubjectDetailState(subjectCompound = it.data))
                    }
                }
        }
    }

    fun deleteSubject(subject: Subject) {
        viewModelScope.launch {
            subjectUseCase
                .deleteSubjectName(subject)
                .catch {  }
                .collect {  }
        }
    }

}
