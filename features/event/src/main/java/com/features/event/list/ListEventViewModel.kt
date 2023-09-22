package com.features.event.list

import androidx.lifecycle.ViewModel
import com.core.common.Result
import com.core.domain.EventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
internal class ListEventViewModel @Inject constructor(
    private val eventUseCase: EventUseCase
): ViewModel(), ContainerHost<ListEventState, ListEventSideEffect>{

    override val container = container<ListEventState, ListEventSideEffect>(ListEventState())

    fun fetchEvents() = intent {
        when(val eventsResult = eventUseCase.fetchEvents()){
            is Result.Error -> {}
            is Result.Success -> reduce { state.copy(events = eventsResult.data) }
        }
    }
}
