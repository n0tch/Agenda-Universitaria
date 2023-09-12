package com.feature.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.HomeUseCase
import com.core.domain.TimetableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val timetableUseCase: TimetableUseCase,
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    init {
        fetchTimeTables()
    }

    private val _uiState: MutableStateFlow<TimetableState> by lazy { MutableStateFlow(TimetableState()) }
    val uiState: StateFlow<TimetableState> = _uiState.asStateFlow()

    private val _timetableState: MutableStateFlow<TimetableMap> =
        MutableStateFlow(TimetableMap())
    val timetableState: StateFlow<TimetableMap> = _timetableState.asStateFlow()

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

    fun fetchTimeTables(){
        viewModelScope.launch {
            homeUseCase
                .fetchWeeklyTimeTable()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> {}

                        is Result.Success ->
                            _timetableState.emit(TimetableMap(items = it.data))
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