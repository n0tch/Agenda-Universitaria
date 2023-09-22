package com.features.note.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
) : ViewModel(), ContainerHost<NoteDetailState, NoteDetailSideEffect> {

    override val container = container<NoteDetailState, NoteDetailSideEffect>(NoteDetailState())

    private val _noteDetailState: MutableStateFlow<NoteDetailState> by lazy {
        MutableStateFlow(NoteDetailState())
    }
    val noteDetailState: StateFlow<NoteDetailState> = _noteDetailState.asStateFlow()

    fun fetchNoteDetail(noteId: Int) = intent {
        val noteResult = noteUseCase.fetchNoteById(noteId)
        when(noteResult){
            is Result.Error -> {}
            is Result.Success -> reduce { state.copy(noteCompound = noteResult.data) }
        }
    }
}
