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
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase,
) : ViewModel() {

    private val _noteDetailState: MutableStateFlow<NoteDetailState> by lazy {
        MutableStateFlow(NoteDetailState())
    }
    val noteDetailState: StateFlow<NoteDetailState> = _noteDetailState.asStateFlow()

    fun fetchNoteDetail(noteId: Int) {
        viewModelScope.launch {
            noteUseCase.fetchNoteById(noteId)
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> Log.e("fetchNoteDetail", it.exception.toString())
                        is Result.Success -> _noteDetailState.emit(NoteDetailState(noteCompound = it.data))
                    }
                }
        }
    }
}
