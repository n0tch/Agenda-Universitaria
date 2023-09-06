package com.feature.timetable.newentry

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.SubjectUseCase
import com.core.domain.TimetableUseCase
import com.core.common.Result
import com.example.model.Timetable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTimetableViewModel @Inject constructor(
    private val timetableUseCase: TimetableUseCase,
    private val subjectUseCase: SubjectUseCase
) : ViewModel() {

    init {
        fetchSubjects()
    }

    val uiState: MutableStateFlow<NewTimetableState> by lazy { MutableStateFlow(NewTimetableState.Idle) }

    fun saveTimetable(entry: Timetable) {
        viewModelScope.launch {
            timetableUseCase.saveTimetableEntry(entry).collect { Log.e("asas", "sssssss") }
        }
    }

    @VisibleForTesting
    private fun fetchSubjects() {
        viewModelScope.launch {
            subjectUseCase.fetchSubjects().collect {
                when (it) {
                    is Result.Error -> {}
                    is Result.Success -> uiState.emit(NewTimetableState.Subjects(it.data))
                }
            }
        }
    }
}