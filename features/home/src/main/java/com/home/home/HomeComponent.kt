package com.home.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.ToastComponent
import java.time.LocalDate

@Composable
fun HomeComponent(
    screenList: List<String> = emptyList(),
    addScreens: List<String> = emptyList(),
    navigateToSubject: (String, Int) -> Unit = {_,_->},
    navigateToScreen: (String) -> Unit = {},
    navigateToAddScreen: (String) -> Unit = {}
) {

    val viewModel: HomeViewModel = hiltViewModel()

    val currentUserState by viewModel.currentUserState.collectAsStateWithLifecycle()
    val examsState by viewModel.examsState.collectAsStateWithLifecycle()
    val dailyTimetableState by viewModel.dailyTimetableState.collectAsStateWithLifecycle()

    when {
        currentUserState.exception != null -> {
            ToastComponent(message = "Erro ao buscar user")
        }

        examsState.exception != null -> {
            ToastComponent(message = "Erro ao buscar exams")
        }
    }

    HomeScreen(
        screenList = screenList,
        timetable = dailyTimetableState.items,
        today = LocalDate.now().dayOfWeek,
        nextExams = examsState.nextExams,
        onWeekDaySelected = {
            viewModel.fetchTimetableBtWeekDay(it)
        },
        onTimetableClicked = {
            navigateToSubject(it.name, it.id)
        },
        onNavigateToScreen = { navigateToScreen(it) }
    )
}

@Preview
@Composable
fun HomeComponentPreview() {
    HomeComponent()
}