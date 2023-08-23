package com.feature.timetable

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.TimetableUseCase
import com.core.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val timetableUseCase: TimetableUseCase
) : ViewModel() {

    init {
        fetchTimeTable()
    }

    val uiState: MutableStateFlow<TimetableState> by lazy { MutableStateFlow(TimetableState.Idle) }

    @VisibleForTesting
    private fun fetchTimeTable() {
        viewModelScope.launch {
            timetableUseCase
                .fetchTimeTable()
                .collect {
                    when (it) {
                        is Result.Error -> {}
                        is Result.Success -> uiState.emit(TimetableState.Entries(it.data))
                    }
                }
        }
    }
}