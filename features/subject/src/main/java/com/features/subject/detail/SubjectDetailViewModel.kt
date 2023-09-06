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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _uiState: MutableStateFlow<SubjectDetailState> by lazy {
        MutableStateFlow(SubjectDetailState())
    }
    val uiState: StateFlow<SubjectDetailState> = _uiState.asStateFlow()


    fun fetchSubjectCompound(subjectId: Int){
        viewModelScope.launch {
            subjectUseCase
                .fetchSubjectCompound(subjectId)
                .flowOn(uiDispatcher)
                .collect {
                    when(it){
                        is Result.Error -> _uiState.emit(SubjectDetailState(exception = it.exception))
                        is Result.Success -> _uiState.emit(SubjectDetailState(subjectCompound = it.data))
                    }
                }
        }
    }

//    fun fetchNotesBySubject(subjectId: Int){
//        viewModelScope.launch {
//            noteUseCase
//                .fetchNoteBySubject(subjectId)
//                .flowOn(uiDispatcher)
//                .collect {
//                    when(it){
//                        is Result.Error -> _uiState.emit(SubjectDetailState(exception = it.exception))
//                        is Result.Success -> _uiState.emit(SubjectDetailState.NoteList(it.data))
//                    }
//                }
//        }
//    }

    fun deleteSubject(subjectId: Int) {
        viewModelScope.launch {
            subjectUseCase
                .deleteSubjectName(subjectId)
                .catch {  }
                .collect {  }
        }
    }

}
