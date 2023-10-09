package com.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.EventUseCase
import com.example.model.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val eventUseCase: EventUseCase,
): ViewModel() {

    val events: MutableStateFlow<Map<Calendar, List<Event>>> = MutableStateFlow(emptyMap())
    val loading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    fun fetchCalendar(){
        viewModelScope.launch {
            val fetchedEvents = eventUseCase.fetchEventsGroupedByDate()
            events.emit(fetchedEvents)
            loading.emit(false)
        }
    }
}