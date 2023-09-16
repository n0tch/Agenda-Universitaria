package com.feature.timetable.newentry

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Result
import com.core.domain.SubjectUseCase
import com.core.domain.TimetableUseCase
import com.example.model.Subject
import com.example.model.Timetable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _subjects: MutableStateFlow<List<Subject>> by lazy { MutableStateFlow(emptyList()) }
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    fun saveTimetables(entries: List<Timetable>, subjectId: Int) {
        viewModelScope.launch {
            timetableUseCase.saveTimetableEntries(entries, subjectId).collect { Log.e("asas", "sssssss") }
        }
    }

    @VisibleForTesting
    private fun fetchSubjects() {
        viewModelScope.launch {
            subjectUseCase.fetchSubjects().collect {
                when (it) {
                    is Result.Error -> {}
                    is Result.Success -> _subjects.emit(it.data)
                }
            }
        }
    }

    fun saveSubject(name: String, place: String, teacher: String) {
        viewModelScope.launch {
            subjectUseCase.saveSubject(
                Subject(name = name, place = place, teacher = teacher)
            ).collect {
                when(it){
                    is Result.Error -> TODO()
                    is Result.Success -> {
                        _subjects.value = _subjects.value.toMutableList().apply { add(it.data) }
                    }
                }
            }
        }
    }
}