package com.features.note.newnote

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.LabelUseCase
import com.core.domain.NoteUseCase
import com.core.domain.SubjectUseCase
import com.example.model.Label
import com.example.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
    private val subjectUseCase: SubjectUseCase,
    private val labelUseCase: LabelUseCase,
) : ViewModel() {

    init {
        fetchSubjects()
        fetchNoteLabels()
    }

    private val _uiState: MutableStateFlow<NoteState> by lazy { MutableStateFlow(NoteState()) }
    val uiState: StateFlow<NoteState> = _uiState.asStateFlow()

    private val _labelState: MutableStateFlow<LabelState> by lazy { MutableStateFlow(LabelState()) }
    val labelState: StateFlow<LabelState> = _labelState.asStateFlow()

    private val _subjectsState: MutableStateFlow<SubjectsState> by lazy { MutableStateFlow(SubjectsState()) }
    val subjectsState: StateFlow<SubjectsState> = _subjectsState.asStateFlow()

    fun saveNote(note: Note, uriPaths: List<String>) {
        viewModelScope.launch {
            noteUseCase.saveNote(note, uriPaths).onStart {
                _uiState.emit(NoteState(isLoading = true))
            }.collect {
                when (it) {
                    is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                    is Result.Success -> _uiState.emit(NoteState(noteSaved = true))
                }
            }
        }
    }

    fun saveNoteLabel(label: Label) {
        viewModelScope.launch {
            labelUseCase.saveNoteLabel(label).collect {
                when (it) {
                    is Result.Error -> {
                        Log.e("erro", it.exception.toString())
                    }

                    is Result.Success -> {
                        Log.e("success", it.data.toString())
                        _labelState.emit(LabelState(labels = _labelState.value.labels.apply { toMutableList().add(label) }))
                    }
                }
            }
        }
    }

    private fun fetchNoteLabels() {
        viewModelScope.launch {
            labelUseCase.fetchNoteLabels().collect {
                when (it) {
                    is Result.Error -> {
                        Log.e("erro", it.exception.toString())
                        _uiState.emit(NoteState(exception = it.exception))
                    }

                    is Result.Success -> {
                        Log.e("success", it.data.toString())
                        _labelState.emit(LabelState(labels = it.data))
                    }
                }
            }
        }
    }

    @VisibleForTesting
    private fun fetchSubjects() {
        viewModelScope.launch {
            subjectUseCase.fetchSubjects().flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> {
                        Log.e(this::class.java.simpleName, it.exception.toString())
                        _uiState.emit(NoteState(exception = it.exception))
                    }

                    is Result.Success -> {
                        Log.e("fetchSubjects", it.data.toString())
                        _subjectsState.emit(SubjectsState(subjects = it.data))
                    }
                }
            }
        }
    }

    fun fetchNote(noteId: Int?) {
        viewModelScope.launch {
            noteUseCase
                .fetchNoteById(noteId)
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> _uiState.emit(NoteState(noteCompound = it.data))
                    }
                }
        }
    }
}