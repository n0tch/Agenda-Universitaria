package com.home.list

import androidx.lifecycle.ViewModel
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.EventUseCase
import com.core.domain.HomeUseCase
import com.core.domain.NoteUseCase
import com.core.domain.UserUseCase
import com.example.model.NotesWithCountCompound
import com.home.list.state.CurrentUserState
import com.home.list.state.EventState
import com.home.list.state.HomeAction
import com.home.list.state.HomeListState
import com.home.list.state.HomeSideEffect
import com.home.list.state.SubjectConfigState
import com.home.list.state.TimetableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val userUseCase: UserUseCase,
    private val homeUseCase: HomeUseCase,
    private val noteUseCase: NoteUseCase,
    private val eventUseCase: EventUseCase,
) : ViewModel(), ContainerHost<HomeListState, HomeSideEffect> {

    override val container = container<HomeListState, HomeSideEffect>(HomeListState())

    init {
        fetchCurrentUser()
        fetchTimetableBtWeekDay(LocalDate.now().dayOfWeek)
    }

    fun fetchTimetableBtWeekDay(dayOfWeek: DayOfWeek) = intent {
        when (val timetableResult = homeUseCase.fetchTimetableByDay(dayOfWeek)) {
            is Result.Error -> postSideEffect(HomeSideEffect.Toast(timetableResult.exception.toString()))
            is Result.Success -> {
                reduce {
                    state.copy(
                        timetableState = TimetableState(
                            selectedDayOfWeek = dayOfWeek,
                            timetables = timetableResult.data
                        )
                    )
                }
            }
        }
    }

    private fun fetchCurrentUser() = intent {
        val userResult = userUseCase
            .fetchCurrentUser()
        when (userResult) {
            is Result.Error -> postSideEffect(HomeSideEffect.Toast(userResult.toString()))
            is Result.Success -> reduce {
                state.copy(
                    currentUserState = CurrentUserState(
                        username = userResult.data.userName,
                        photoUrl = userResult.data.photoUrl,
                        email = userResult.data.email
                    )
                )
            }
        }
    }

    fun fetchLatestNotes() = intent {
        val notesResult = noteUseCase.fetchNotesWithCount(LATEST_TEN)

        when (notesResult) {
            is Result.Error -> postSideEffect(HomeSideEffect.Toast(notesResult.exception.toString()))
            is Result.Success -> reduce {
                state.copy(
                    noteState = NotesWithCountCompound(
                        notesResult.data.note,
                        notesResult.data.count
                    )
                )
            }
        }
    }

    fun fetchLatestEvents() = intent {
        val eventsResult = eventUseCase.fetchEvents(LATEST_TEN)

        when(eventsResult){
            is Result.Error -> postSideEffect(HomeSideEffect.Toast(eventsResult.exception.toString()))
            is Result.Success -> reduce { state.copy(eventsState = EventState(eventsResult.data)) }
        }
    }

    fun setAction(homeActon: HomeAction) {
        when (homeActon) {
            is HomeAction.DaySelected -> fetchTimetableBtWeekDay(homeActon.dayOfWeek)
            is HomeAction.EventSelected -> {}
            is HomeAction.NoteClicked -> {}
            is HomeAction.TimeTableConfig -> intent {
                reduce {
                    state.copy(
                        subjectConfigState = SubjectConfigState(
                            true,
                            homeActon.subject
                        )
                    )
                }
            }
        }
    }

    fun onDismissDialog() = intent {
        reduce { state.copy(subjectConfigState = SubjectConfigState(false)) }
    }

    companion object {
        private const val LATEST_TEN = 10
    }
}
