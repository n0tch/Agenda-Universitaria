package com.features.note.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.NoteUseCase
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

    val uiState: MutableStateFlow<NoteListState> by lazy { MutableStateFlow(NoteListState.Idle) }

    fun fetchNotes() {
        viewModelScope.launch {
            noteUseCase.fetchNotes().flowOn(uiDispatcher).collect {
                when(it){
                    is Result.Error -> uiState.emit(NoteListState.Error(it.exception))
                    is Result.Success -> uiState.emit(NoteListState.NoteList(it.data))
                }
            }
        }
    }

}