package com.features.note.list

import androidx.lifecycle.ViewModel
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.LabelUseCase
import com.core.domain.NoteUseCase
import com.example.model.Label
import com.example.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
    private val labelUseCase: LabelUseCase
) : ViewModel(), ContainerHost<NoteListState, NoteListSideEffect> {

    override val container = container<NoteListState, NoteListSideEffect>(NoteListState())

    fun fetchNotes() = intent {
        when(val notesResult = noteUseCase.fetchNotes()){
            is Result.Error -> postSideEffect(NoteListSideEffect.Toast(notesResult.exception.toString()))
            is Result.Success -> reduce { state.copy(notes = notesResult.data) }
        }
    }

    fun searchNote(query: String) = intent {
        when(val notesResult = noteUseCase.searchNotes(query)){
            is Result.Error -> postSideEffect(NoteListSideEffect.Toast(notesResult.exception.toString()))
            is Result.Success -> reduce { state.copy(notes = notesResult.data) }
        }
    }

    fun deleteNote(note: Note) = intent {
        when(val deletedResult = noteUseCase.deleteNote(note)){
            is Result.Error -> postSideEffect(NoteListSideEffect.Toast(deletedResult.exception.toString()))
            is Result.Success -> reduce { state.copy(noteDeleted = deletedResult.data) }
        }
    }

    fun searchNotesByLabel(label: Label) = intent {
        when(val notesResult = noteUseCase.fetchNotesByLabel(label)){
            is Result.Error -> postSideEffect(NoteListSideEffect.Toast(notesResult.exception.toString()))
            is Result.Success -> reduce { state.copy(notes = notesResult.data) }
        }
    }

    fun fetchNoteLabels() = intent {
        when(val labelsResult = labelUseCase.fetchNoteLabels()){
            is Result.Error -> postSideEffect(NoteListSideEffect.Toast(labelsResult.exception.toString()))
            is Result.Success -> reduce { state.copy(labels = labelsResult.data) }
        }
    }

    fun setSideEffect() = intent {
        postSideEffect(NoteListSideEffect.ShowFilter)
    }
}