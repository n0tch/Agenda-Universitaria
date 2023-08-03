package com.features.subject.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.NoteUseCase
import com.core.domain.SubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val subjectUseCase: SubjectUseCase,
    private val noteUseCase: NoteUseCase
): ViewModel() {

    val uiState: MutableStateFlow<SubjectDetailState> by lazy { MutableStateFlow(SubjectDetailState.Idle) }

    fun fetchNotesBySubject(subject: String){
        viewModelScope.launch {
            noteUseCase
                .fetchNoteBySubject(subject)
                .flowOn(uiDispatcher)
                .collect {
                    when(it){
                        is Result.Error -> uiState.emit(SubjectDetailState.Error(it.exception))
                        is Result.Success -> uiState.emit(SubjectDetailState.NoteList(it.data))
                    }
                }
        }
    }

    fun deleteSubject(subjectName: String) {
        viewModelScope.launch {
            subjectUseCase
                .deleteSubjectName(subjectName)
                .catch {  }
                .collect {  }
        }
    }

}
