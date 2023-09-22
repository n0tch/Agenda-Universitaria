package com.features.event.edit

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.EventUseCase
import com.core.domain.LabelUseCase
import com.core.domain.SubjectUseCase
import com.example.model.event.EventSaveRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
internal class EditEventViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val eventUseCase: EventUseCase,
    private val subjectUseCase: SubjectUseCase,
    private val labelUseCase: LabelUseCase
) : ViewModel(), ContainerHost<EditEventState, EditEventSideEffect> {

    override val container = container<EditEventState, EditEventSideEffect>(EditEventState(isLoading = true))

    init {
        fetchSubjects()
        fetchLabels()
    }

    @VisibleForTesting
    private fun fetchSubjects() = intent {
        viewModelScope.launch {
            when(val subjectsResult = subjectUseCase.fetchSubjects()){
                is Result.Error -> {}
                is Result.Success ->{
                    reduce { state.copy(subjects = subjectsResult.data) }
                }
            }
        }
    }

    fun fetchLabels() = intent {
        viewModelScope.launch {
            when(val labels = labelUseCase.fetchNoteLabels()){
                is Result.Error -> {}
                is Result.Success -> reduce { state.copy(isLoading = false, labels = labels.data) }//_labels.emit(labels.data)
            }
        }
    }

    fun saveEvent(
        event: EventSaveRequest
    ) = intent {
        viewModelScope.launch {
            when(eventUseCase.saveEvent(event)){
                is Result.Error -> {}
                is Result.Success -> reduce { state.copy(saved = true) }
            }
        }
    }

    fun setAction(editEventAction: EditEventAction) = intent {
        when(editEventAction){
            EditEventAction.OnBack -> postSideEffect(EditEventSideEffect.OnBack)
            is EditEventAction.SaveEvent -> saveEvent(editEventAction.eventSaveRequest)
        }
    }
}