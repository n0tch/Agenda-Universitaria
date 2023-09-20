package com.features.event.list

import androidx.lifecycle.viewModelScope
import com.core.common.Result
import com.core.common.viewmodel.BaseViewModel
import com.core.domain.EventUseCase
import com.example.model.event.EventCompound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListEventViewModel @Inject constructor(
    private val eventUseCase: EventUseCase
): BaseViewModel<ListEventAction, ListEventNavigation>(){

    init {
        fetchEvents()
    }

    private val _events: MutableStateFlow<List<EventCompound>> = MutableStateFlow(emptyList())
    val events: StateFlow<List<EventCompound>> = _events.asStateFlow()

    fun fetchEvents(){
        viewModelScope.launch {
            eventUseCase.fetchEvents().collect{ result ->
                when(result){
                    is Result.Error -> {}
                    is Result.Success -> _events.update { result.data }
                }
            }
        }
    }
}
