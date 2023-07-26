package com.features.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
//    private val noteUseCase: NoteUseCase
): ViewModel() {

    init {
//        saveNote()
    }

    fun saveNote(){
//        viewModelScope.launch(ioDispatcher) {
//            noteUseCase.saveNewNote(Note("new", "body"))
//        }
    }

}