package com.home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.ExamUseCase
import com.core.domain.HomeUseCase
import com.core.domain.LoginUseCase
import com.core.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase,
    private val homeUseCase: HomeUseCase,
    private val examUseCase: ExamUseCase
) : ViewModel() {

    private val _currentUserState: MutableStateFlow<CurrentUserState> =
        MutableStateFlow(CurrentUserState())
    val currentUserState: StateFlow<CurrentUserState> = _currentUserState.asStateFlow()

    private val _examsState: MutableStateFlow<ExamsState> =
        MutableStateFlow(ExamsState())
    val examsState: StateFlow<ExamsState> = _examsState.asStateFlow()

    private val _logoutState: MutableStateFlow<LogoutState> =
        MutableStateFlow(LogoutState())
    val logoutState: StateFlow<LogoutState> = _logoutState.asStateFlow()

    private val _timetableState: MutableStateFlow<HomeTimetableState> =
        MutableStateFlow(HomeTimetableState())
    val timetableState: StateFlow<HomeTimetableState> = _timetableState.asStateFlow()

    fun fetchTimetableByWeekDay() {
        viewModelScope.launch {
            homeUseCase
                .fetchWeeklyTimeTable()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error ->
                            _timetableState.emit(HomeTimetableState(exception = it.exception))

                        is Result.Success ->
                            _timetableState.emit(HomeTimetableState(items = it.data))
                    }
                }
        }
    }

    fun fetchCurrentUser() {
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

    fun logout() {
        viewModelScope.launch {
            loginUseCase.logout()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> _logoutState.emit(LogoutState(exception = it.exception))
                        is Result.Success -> _logoutState.emit(LogoutState(logoutSuccess = it.data))
                    }
                }
        }
    }

    fun fetchNextExams() {
        viewModelScope.launch {
            examUseCase.fetchNextExams()
                .flowOn(uiDispatcher)
                .collect {
                    when (it) {
                        is Result.Error -> {
                            _examsState.emit(ExamsState(exception = it.exception))
                        }

                        is Result.Success -> {
                            Log.e("next exams", it.data.toString())
                            _examsState.emit(ExamsState(nextExams = it.data))
                        }
                    }
                }
        }
    }
}
