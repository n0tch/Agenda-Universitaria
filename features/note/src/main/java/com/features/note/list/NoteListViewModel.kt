package com.features.note.list

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.LabelUseCase
import com.core.domain.NoteUseCase
import com.example.model.Label
import com.example.model.Note
import com.features.note.edit.LabelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
    private val labelUseCase: LabelUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<NoteState> by lazy { MutableStateFlow(NoteState()) }
    val uiState: StateFlow<NoteState> = _uiState.asStateFlow()

    private val _labelState: MutableStateFlow<LabelState> by lazy { MutableStateFlow(LabelState()) }
    val labelState: StateFlow<LabelState> = _labelState.asStateFlow()

    fun fetchNotes() {
        viewModelScope.launch {
            noteUseCase
                .fetchNotes()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> _uiState.emit(NoteState(notes = it.data))
                    }
                }
        }
    }

    fun searchNote(query: String) {
        viewModelScope.launch {
            noteUseCase
                .searchNotes(query)
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> _uiState.emit(NoteState(notes = it.data))
                    }
                }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteUseCase
                .deleteNote(note)
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> {
                            _uiState.emit(NoteState(notes = uiState.value.notes.filterNot { it.note.id == note.id }))
                        }
                    }
                }
        }
    }

    fun searchNotesByLabel(label: Label) {
        viewModelScope.launch {
            noteUseCase.fetchNotesByLabel(label).flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> Log.e("searchNotesByLabel", it.exception.toString())
                    is Result.Success -> _uiState.emit(NoteState(notes = it.data))
                }
            }
        }
    }

    fun fetchNoteLabels() {
        viewModelScope.launch {
            labelUseCase.fetchNoteLabels().flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                    is Result.Success -> _labelState.emit(LabelState(labels = it.data))
                }
            }
        }
    }
}