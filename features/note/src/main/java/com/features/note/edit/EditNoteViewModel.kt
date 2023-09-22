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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
    private val subjectUseCase: SubjectUseCase,
    private val labelUseCase: LabelUseCase,
) : ViewModel(), ContainerHost<EditNoteState, EditNoteSideEffect> {

    override val container =
        container<EditNoteState, EditNoteSideEffect>(EditNoteState(isLoading = true))

    init {
        fetchLabels()
        fetchSubjects()
    }

    fun saveNote(note: Note, uriPaths: List<Uri?>, labels: List<Label>) = intent {
        when (val saveNoteResult = noteUseCase.saveNote(note, uriPaths, labels)) {
            is Result.Error -> postSideEffect(EditNoteSideEffect.Toast(saveNoteResult.exception.message.toString()))
            is Result.Success -> reduce { state.copy(noteSaved = saveNoteResult.data) }
        }
    }

    fun updateNote(note: Note, uriPaths: List<Uri?>, labels: List<Label>) = intent {
        when (val updatedNoteResult = noteUseCase.updateNote(note, uriPaths, labels)) {
            is Result.Error -> postSideEffect(EditNoteSideEffect.Toast(updatedNoteResult.exception.message.toString()))
            is Result.Success -> reduce { state.copy(noteSaved = true) }
        }
    }

    @VisibleForTesting
    private fun fetchLabels() = intent {
        viewModelScope.launch {
            when (val labels = labelUseCase.fetchNoteLabels()) {
                is Result.Error -> reduce { state.copy(isLoading = false) }
                is Result.Success -> {
                    reduce { state.copy(labels = labels.data) }
                }
            }
        }
    }

    fun fetchSubjects() = intent {
        when (val subjectResult = subjectUseCase.fetchSubjects()) {
            is Result.Error -> {}
            is Result.Success -> {
                reduce { state.copy(subjects = subjectResult.data) }
            }
        }
    }

    fun saveNoteLabel(label: String) = intent {
        when (val result = labelUseCase.saveLabel(Label(name = label))) {
            is Result.Error -> {}
            is Result.Success -> {
                reduce {
                    state.copy(labels = state.labels.toMutableList().apply { add(result.data) })
                }
            }
        }
    }

    fun fetchNote(noteId: Int) = intent {
        reduce { state.copy(isLoading = true) }
        when (val noteResult = noteUseCase.fetchNoteById(noteId)) {
            is Result.Error -> {
                reduce { state.copy(isLoading = false) }
                postSideEffect(EditNoteSideEffect.Toast(noteResult.exception.message.toString()))
            }
            is Result.Success -> reduce {
                state.copy(
                    isLoading = false,
                    noteCompound = noteResult.data
                )
            }
        }
    }

    fun saveSubject(name: String, place: String, teacher: String) {
        viewModelScope.launch {
            subjectUseCase.saveSubject(Subject(name = name, place = place, teacher = teacher))
                .collect {
                    when (it) {
                        is Result.Error -> {}
                        is Result.Success -> {
                            intent {
                                reduce {
                                    state.copy(
                                        subjects = state.subjects.toMutableList()
                                            .apply { add(it.data) })
                                }
                            }
                        }
                    }
                }
        }
    }
}