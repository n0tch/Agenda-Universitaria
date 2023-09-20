package com.features.note.edit

import android.net.Uri
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
import com.example.model.Subject
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
class EditNoteViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
    private val subjectUseCase: SubjectUseCase,
    private val labelUseCase: LabelUseCase,
) : ViewModel() {

    init {
        fetchSubjectAndLabels()
    }

    private val _uiState: MutableStateFlow<NoteState> by lazy { MutableStateFlow(NoteState(isLoading = true)) }
    val uiState: StateFlow<NoteState> = _uiState.asStateFlow()

    private val _labelState: MutableStateFlow<List<Label>> by lazy { MutableStateFlow(emptyList()) }
    val labelState: StateFlow<List<Label>> = _labelState.asStateFlow()

    private val _subjects: MutableStateFlow<List<Subject>> by lazy { MutableStateFlow(emptyList()) }
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    fun saveNote(note: Note, uriPaths: List<Uri?>, labels: List<Label>) {
        viewModelScope.launch {
            noteUseCase.saveNote(note, uriPaths, labels).onStart {
                _uiState.emit(NoteState(isLoading = true))
            }.flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                    is Result.Success -> _uiState.emit(NoteState(noteSaved = true))
                }
            }
        }
    }

    fun updateNote(note: Note, uriPaths: List<Uri?>, labels: List<Label>) {
        viewModelScope.launch {
            noteUseCase.updateNote(note, uriPaths, labels).flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> _uiState.emit(NoteState(exception = it.exception))
                    is Result.Success -> _uiState.emit(NoteState(noteSaved = true))
                }
            }
        }
    }

    @VisibleForTesting
    private fun fetchSubjectAndLabels() {
        viewModelScope.launch {
            val labels = labelUseCase.fetchNoteLabels()

            when (labels) {
                is Result.Error -> {}
                is Result.Success -> _labelState.emit(labels.data)
            }

            subjectUseCase.fetchSubjects().collect {
                when (it) {
                    is Result.Error -> {}
                    is Result.Success -> _subjects.emit(it.data)
                }
            }
        }
    }

    fun saveNoteLabel(label: String) {
        viewModelScope.launch {
            labelUseCase.saveNoteLabel(Label(name = label)).flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> {
                        Log.e("erro", it.exception.toString())
                    }

                    is Result.Success -> {
                        Log.e("success", it.data.toString())
                        _labelState.value = _labelState.value.toMutableList().apply { add(it.data) }
                    }
                }
            }
        }
    }

    fun fetchNote(noteId: Int) {
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

    fun saveSubject(name: String, place: String, teacher: String) {
        viewModelScope.launch {
            subjectUseCase.saveSubject(Subject(name = name, place = place, teacher = teacher))
                .collect {
                    when (it) {
                        is Result.Error -> TODO()
                        is Result.Success -> _subjects.value =
                            _subjects.value.toMutableList().apply { add(it.data) }
                    }
                }
        }
    }
}