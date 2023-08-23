package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.ToastComponent
import com.example.model.TimetableEntry
import com.home.home.drawer.DrawerBody
import kotlinx.coroutines.launch

@Composable
fun HomeComponent(
    screenList: List<String>,
    onNavigateToNote: (String) -> Unit,
    onNavigateToNoteGraph: () -> Unit,
    navigateToScreen: (String) -> Unit,
    onLogout: () -> Unit
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentUserState by viewModel.currentUserState.collectAsStateWithLifecycle()
    val examsState by viewModel.examsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchCurrentUser()
        viewModel.fetchNextExams()
    })

    var timetable: List<TimetableEntry> = remember {
        mutableStateListOf(
            TimetableEntry(
                id = "",
                weekDays = listOf("segunda", "terca"),
                startTime = "10:00",
                endTime = "12:00",
                subjectId = "Direito"
            ),
            TimetableEntry(
                id = "",
                weekDays = listOf("segunda", "terca"),
                startTime = "13:00",
                endTime = "15:00",
                subjectId = "Filosofia"
            )
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

    when{
        currentUserState.exception != null -> {
            ToastComponent(message = "Erro ao buscar user")
        }
        examsState.exception != null -> {
            ToastComponent(message = "Erro ao buscar exams")
        }
    }
//    when (uiState) {
//        is HomeState.HomeCurrentUser -> {}
//
//        HomeState.HomeIdle -> {}
//        is HomeState.HomeCurrentUserError ->
//            ToastComponent(message = (uiState as HomeState.HomeCurrentUserError).exception.message.toString())
//
//        is LogoutState.LogoutError ->
//            ToastComponent(message = (uiState as LogoutState.LogoutError).exception.message.toString())
//
//        is LogoutState.LogoutSuccess -> {
//            LaunchedEffect(key1 = Unit, block = {
//                onLogout()
//            })
//        }
//
//        is HomeState.Error -> {}
//        is HomeState.HomeNextExams -> {}
//    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier) {
                DrawerBody(
                    photoUrl = currentUserState.photoUrl,
                    screensList = screenList
                ) { screenName ->
                    coroutine.launch { drawerState.close() }
                    navigateToScreen(screenName)
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                HomeScreen(
                    currentUserState = currentUserState,
                    timetable = timetable,
                    onProfileClick = {
                        if (drawerState.isOpen) {
                            coroutine.launch { drawerState.close() }
                        } else {
                            coroutine.launch { drawerState.open() }
                        }
                    },
                    nextExams = examsState.nextExams,
                    onFloatingActionButtonClicked = {
                        viewModel.fetchTimetableByWeekDay()
                    })
            }
        })
}

@Preview
@Composable
fun HomeComponentPreview() {
    HomeComponent(
        listOf("A", "B", "C"),
        onNavigateToNote = {},
        onNavigateToNoteGraph = { },
        onLogout = {},
        navigateToScreen = {}
    )
}