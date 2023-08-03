package com.home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.HomeUseCase
import com.core.domain.LoginUseCase
import com.core.domain.NoteUseCase
import com.core.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase,
    private val noteUseCase: NoteUseCase,
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    init {
        fetchCurrentUser()
//        fetchTimetableByWeekDay()
    }

    val uiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.HomeIdle)

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
        viewModelScope.launch(ioDispatcher) {
            userUseCase.fetchCurrentUser().flowOn(uiDispatcher).collect {
                when (it) {
                    is Result.Error -> uiState.value = HomeState.HomeCurrentUserError(it.exception)
                    is Result.Success -> uiState.value = HomeState.HomeCurrentUser(it.data)
                }
            }
        }
    }

    fun fetchNotes() {
        viewModelScope.launch {
            noteUseCase.fetchNotes()
                .flowOn(uiDispatcher)
                .onStart { }
                .collect {
                    when (it) {
                        is Result.Error -> {
                            Log.e("error", it.exception.toString())
                        }

                        is Result.Success -> {
                            it.data.forEach {
                                Log.e("success", "${it.id} - ${it.title} - ${it.body}")
                            }
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

}