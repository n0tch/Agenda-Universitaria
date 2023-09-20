package com.features.event.edit

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.common.viewmodel.BaseViewModel
import com.core.domain.EventUseCase
import com.core.domain.LabelUseCase
import com.core.domain.SubjectUseCase
import com.example.model.Label
import com.example.model.Subject
import com.example.model.event.EventCompound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditEventViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val eventUseCase: EventUseCase,
    private val subjectUseCase: SubjectUseCase,
    private val labelUseCase: LabelUseCase
) : BaseViewModel<EditEventAction, EditEventNavigation>() {

    init {
        fetchSubjects()
        fetchLabels()
    }

    private val _subjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    private val _labels: MutableStateFlow<List<Label>> by lazy { MutableStateFlow(emptyList()) }
    val labels: StateFlow<List<Label>> = _labels.asStateFlow()

    @VisibleForTesting
    private fun fetchSubjects(){
        viewModelScope.launch {
            subjectUseCase
                .fetchSubjects()
                .flowOn(uiDispatcher)
                .collect {
                    when(it){
                        is Result.Error -> {}
                        is Result.Success ->
                            _subjects.emit(it.data)
                    }
                }
        }
    }

    fun fetchLabels() {
        viewModelScope.launch {
            when(val labels = labelUseCase.fetchNoteLabels()){
                is Result.Error -> TODO()
                is Result.Success -> _labels.emit(labels.data)
            }
        }
    }

    fun saveEvent(eventCompound: EventCompound, subjectId: Int) {
        viewModelScope.launch {
            eventUseCase.saveEvent(eventCompound, subjectId).collect {
                when (it) {
                    is Result.Error -> {}
                    is Result.Success -> {
                        Log.e("save event","${it.data}")
                    }
                }
            }
        }
    }


}