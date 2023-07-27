package com.features.note

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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val noteUseCase: NoteUseCase
): ViewModel() {

    val uiState: MutableStateFlow<NoteState> by lazy { MutableStateFlow(NoteState.NoteIdle) }

    fun saveNote(title: String, description: String){
        viewModelScope.launch {
            noteUseCase.saveNote(title, description).onStart {
                uiState.emit(NoteState.NoteIdle)
            }.collect {
                when(it){
                    is Result.Error -> uiState.emit(NoteState.NoteException(it.exception))
                    is Result.Success -> uiState.emit(NoteState.NoteSaved(it.data))
                }
            }
        }
    }

}