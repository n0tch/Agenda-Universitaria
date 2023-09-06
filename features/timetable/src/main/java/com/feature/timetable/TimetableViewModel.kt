package com.feature.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Result
import com.core.domain.TimetableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val timetableUseCase: TimetableUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<TimetableState> by lazy { MutableStateFlow(TimetableState()) }
    val uiState: StateFlow<TimetableState> = _uiState.asStateFlow()

    fun fetchTimetableByDay(weekDay: DayOfWeek){
        viewModelScope.launch {
            timetableUseCase
                .fetchTimetableByWeekDay(weekDay)
                .collect {
                    when(it){
                        is Result.Error -> _uiState.emit(TimetableState(exception = it.exception))
                        is Result.Success -> _uiState.emit(TimetableState(items = it.data))
                    }
                }
        }
    }

    fun saveTimetableNotification(millisToSchedule: List<Long>) {
        viewModelScope.launch {
            timetableUseCase.scheduleTimeTableNotification(millisToSchedule)
        }
    }
}