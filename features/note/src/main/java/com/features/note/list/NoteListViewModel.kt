package com.features.note.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.NoteUseCase
import com.example.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    init {
        fetchNotes()
    }

    val uiState: MutableStateFlow<NoteState> by lazy { MutableStateFlow(NoteState()) }

    fun fetchNotes() {
        viewModelScope.launch {
            noteUseCase
                .fetchNotes()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> uiState.emit(NoteState(notes = it.data))
                    }
                }
        }
    }

    fun searchNote(query: String){
        viewModelScope.launch {
            noteUseCase
                .searchNotes(query)
                .flowOn(uiDispatcher)
                .collect {
                    when(it){
                        is Result.Error -> uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> uiState.emit(NoteState(notes = it.data))
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
                    when(it){
                        is Result.Error -> uiState.emit(NoteState(exception = it.exception))
                        is Result.Success -> {
                            uiState.emit(NoteState(notes = uiState.value.notes.filterNot { it.id == note.id }))
                        }
                    }
                }
        }
    }
}