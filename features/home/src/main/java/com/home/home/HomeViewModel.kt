package com.home.home

import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.ExamUseCase
import com.core.domain.HomeUseCase
import com.core.domain.LoginUseCase
import com.core.domain.NoteUseCase
import com.core.domain.UserUseCase
import com.example.model.Subject
import com.home.home.base.BaseViewModel
import com.home.home.navigation.HomeNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase,
    private val homeUseCase: HomeUseCase,
    private val examUseCase: ExamUseCase,
    private val noteUseCase: NoteUseCase
) : BaseViewModel<HomeActon, HomeNavigation>() {

    init {
        fetchCurrentUser()
        fetchTimetableBtWeekDay(LocalDate.now().dayOfWeek)
    }

    private val _currentUserState: MutableStateFlow<CurrentUserState> =
        MutableStateFlow(CurrentUserState())
    val currentUserState: StateFlow<CurrentUserState> = _currentUserState.asStateFlow()

    private val _examsState: MutableStateFlow<ExamsState> =
        MutableStateFlow(ExamsState())
    val examsState: StateFlow<ExamsState> = _examsState.asStateFlow()

    private val _latestNotes: MutableStateFlow<NoteState> = MutableStateFlow(NoteState())
    val latestNotes: StateFlow<NoteState> = _latestNotes.asStateFlow()

    private val _dailyTimetableState: MutableStateFlow<TimetableState> =
        MutableStateFlow(TimetableState())
    val dailyTimetableState: StateFlow<TimetableState> = _dailyTimetableState.asStateFlow()

    private val _subjectConfigState: MutableStateFlow<SubjectConfigState> =
        MutableStateFlow(SubjectConfigState())
    val subjectConfigState: StateFlow<SubjectConfigState> = _subjectConfigState.asStateFlow()

//    private val _actionState: MutableStateFlow<HomeActon> = MutableStateFlow(HomeActon.NoAction)
//    val actionState: StateFlow<HomeActon> = _actionState.asStateFlow()

    fun fetchTimetableBtWeekDay(dayOfWeek: DayOfWeek) {
        viewModelScope.launch {
            homeUseCase.fetchTimetableByDay(dayOfWeek)
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> {}

                        is Result.Success -> {
                            _dailyTimetableState.update { state ->
                                state.copy(selectedDayOfWeek = dayOfWeek, timetables = it.data)
                            }
                        }
                    }
                }
        }
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            userUseCase
                .fetchCurrentUser()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> {
                            _currentUserState.emit(CurrentUserState(exception = it.exception))
                        }

                        is Result.Success -> {
                            _currentUserState.emit(
                                CurrentUserState(
                                    username = it.data.userName,
                                    photoUrl = it.data.photoUrl,
                                    email = it.data.email
                                )
                            )
                        }
                    }
                }
        }
    }

    fun fetchNextExams() {
        viewModelScope.launch {
            examUseCase.fetchNextExams()
                .combine(examUseCase.fetchExamsCount()) { examResult, countResult ->
                    when (examResult is Result.Success && countResult is Result.Success) {
                        true -> {
                            ExamsState(nextExams = examResult.data, totalCount = countResult.data)
                        }

                        else -> {
                            ExamsState(exception = Exception())
                        }
                    }
                }
                .flowOn(uiDispatcher)
                .collect {
                    _examsState.emit(it)
                }
        }
    }

    fun fetchLatestNotes() {
        viewModelScope.launch {
            noteUseCase
                .fetchNotes(LATEST_TEN_NOTES)
                .combine(noteUseCase.fetchNoteCount()) { noteResult, countResult ->
                    when (noteResult is Result.Success && countResult is Result.Success) {
                        true -> {
                            NoteState(notes = noteResult.data, totalCount = countResult.data)
                        }

                        else -> {
                            NoteState(exception = Exception("Error"))
                        }
                    }
                }
                .flowOn(uiDispatcher)
                .collect {
                    _latestNotes.emit(it)
                }
        }
    }

    fun onShowConfigDialog(subject: Subject) {
        _subjectConfigState.update { state ->
            state.copy(showDialog = true, subject = subject)
        }
    }

    fun onDismissDialog() {
        _subjectConfigState.update { it.copy(showDialog = false) }
    }

//    fun handleAction(action: HomeActon) {
//        viewModelScope.launch(uiDispatcher) {
//            _actionState.emit(action)
//        }
//    }

    companion object {
        private const val LATEST_TEN_NOTES = 10
    }
}
