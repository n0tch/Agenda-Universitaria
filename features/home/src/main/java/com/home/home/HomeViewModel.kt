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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase,
    private val homeUseCase: HomeUseCase,
    private val examUseCase: ExamUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.HomeIdle)

    private val _currentUserState: MutableStateFlow<CurrentUserState> = MutableStateFlow(CurrentUserState())
    val currentUserState: StateFlow<CurrentUserState> = _currentUserState.asStateFlow()

    private val _examsState: MutableStateFlow<ExamsState> = MutableStateFlow(ExamsState())
    val examsState: StateFlow<ExamsState> = _examsState.asStateFlow()

    fun fetchTimetableByWeekDay(){
        val today = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        viewModelScope.launch {
            homeUseCase.fetchTimetableByDay(today).collect {
                when(it){
                    is Result.Error -> {}
                    is Result.Success -> {}
                }
            }
        }
    }

    fun fetchCurrentUser() {
        viewModelScope.launch {
            userUseCase.fetchCurrentUser().flowOn(uiDispatcher).collect {
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
                .onStart { uiState.emit(HomeState.HomeIdle) }
                .collect {
                    when (it) {
                        is Result.Error -> uiState.emit(LogoutState.LogoutError(it.exception))
                        is Result.Success -> uiState.emit(LogoutState.LogoutSuccess(it.data))
                    }
                }
        }
    }

    fun fetchNextExams() {
        viewModelScope.launch {
            examUseCase.fetchNextExams()
                .flowOn(uiDispatcher)
                .collect {
                    when(it){
                        is Result.Error -> {
                            _examsState.emit(ExamsState(exception = it.exception))
                            uiState.emit(HomeState.Error(it.exception))
                        }
                        is Result.Success -> {
                            _examsState.emit(ExamsState(nextExams = it.data))
                            Log.e("next exams", it.data.toString())
                            uiState.emit(HomeState.HomeNextExams(it.data))
                        }
                    }
                }
        }
    }
}
