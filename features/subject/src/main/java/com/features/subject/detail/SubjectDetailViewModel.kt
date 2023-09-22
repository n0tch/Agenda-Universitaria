package com.features.subject.detail

import androidx.lifecycle.ViewModel
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.EventUseCase
import com.core.domain.NoteUseCase
import com.core.domain.SubjectUseCase
import com.core.domain.TimetableUseCase
import com.example.model.Subject
import com.example.model.Timetable
import com.example.model.event.EventCompound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val subjectUseCase: SubjectUseCase,
    private val eventUseCase: EventUseCase,
    private val noteUseCase: NoteUseCase,
    private val timetableUseCase: TimetableUseCase,
) : ViewModel(), ContainerHost<SubjectDetailState, SubjectDetailSideEffect> {

    override val container = container<SubjectDetailState, SubjectDetailSideEffect>(
        SubjectDetailState()
    )

    private val _uiState: MutableStateFlow<SubjectDetailState> by lazy {
        MutableStateFlow(
            SubjectDetailState()
        )
    }
    val uiState: StateFlow<SubjectDetailState> = _uiState.asStateFlow()

    private val _events: MutableStateFlow<List<EventCompound>> by lazy { MutableStateFlow(emptyList()) }
    val events: StateFlow<List<EventCompound>> = _events.asStateFlow()

    private val _timetableState: MutableStateFlow<Map<DayOfWeek, List<Timetable>>> =
        MutableStateFlow(mapOf())
    val timetableState: StateFlow<Map<DayOfWeek, List<Timetable>>> = _timetableState.asStateFlow()

    fun fetchSubjectWithTimetable(subjectId: Int) = intent {
        when (val subjectResult = subjectUseCase.fetchSubject(subjectId)) {
            is Result.Error -> postSideEffect(SubjectDetailSideEffect.Toast(subjectResult.exception.message.toString()))
            is Result.Success -> reduce { state.copy(subjectCompound = subjectResult.data) }
        }
    }

    fun fetchEvents(subjectId: Int) = intent {
        when (val eventResult = eventUseCase.fetchEventBySubjectId(subjectId)) {
            is Result.Error -> postSideEffect(SubjectDetailSideEffect.Toast(eventResult.exception.message.toString()))
            is Result.Success -> reduce { state.copy(events = eventResult.data) }
        }
    }

    fun deleteSubject(subject: Subject) = intent {
        when (val timetableResult = subjectUseCase.deleteSubjectName(subject)) {
            is Result.Error -> postSideEffect(SubjectDetailSideEffect.Toast(timetableResult.exception.message.toString()))
            is Result.Success -> postSideEffect(SubjectDetailSideEffect.Toast("deleted."))
        }
    }

    fun fetchNotes(subjectId: Int) = intent {
        when(val notesResult = noteUseCase.fetchNoteBySubjectId(subjectId)){
            is Result.Error -> postSideEffect(SubjectDetailSideEffect.Toast(notesResult.exception.message.toString()))
            is Result.Success -> reduce { state.copy(notesWithLabelCompound = notesResult.data) }
        }
    }
}

