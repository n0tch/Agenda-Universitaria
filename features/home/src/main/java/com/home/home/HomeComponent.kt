package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.ToastComponent
import com.home.home.navigation.HomeNavigation
import kotlinx.coroutines.launch

@Composable
fun HomeComponent(
    screenList: List<String> = emptyList(),
    onNavigation: (HomeNavigation) -> Unit = {}
) {

    val viewModel: HomeViewModel = hiltViewModel()

    val currentUserState by viewModel.currentUserState.collectAsStateWithLifecycle()
    val examsState by viewModel.examsState.collectAsStateWithLifecycle()
    val dailyTimetable by viewModel.dailyTimetableState.collectAsStateWithLifecycle()
    val notesState by viewModel.latestNotes.collectAsStateWithLifecycle()

    val selectedSubjectState by viewModel.subjectConfigState.collectAsStateWithLifecycle()

    val homeActions by viewModel.actionState.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNextExams()
        viewModel.fetchLatestNotes()
    })

    LaunchedEffect(homeActions){
        when (val action = homeActions) {
            is HomeActon.DaySelected -> viewModel.fetchTimetableBtWeekDay(action.dayOfWeek)
            is HomeActon.ExamSelected -> {}
            is HomeActon.NoteClicked -> {}
            is HomeActon.TimeTableConfig -> viewModel.onShowConfigDialog(action.subject)
            else -> {}
        }
    }

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
        timetableState = dailyTimetable,
        notesState = notesState,
        examsState = examsState,
        onAction = { viewModel.setAction(it) },
        onNavigation = { }
    )

    ShowBottomSheet(selectedSubjectState) { viewModel.onDismissDialog() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheet(
    subjectConfigState: SubjectConfigState,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutine = rememberCoroutineScope()

    if (subjectConfigState.showDialog) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                onDismiss()
                coroutine.launch { sheetState.hide() }
            }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(subjectConfigState.subject.name)
                Text(subjectConfigState.subject.teacher)
                Text(subjectConfigState.subject.place)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = if (subjectConfigState.subject.notificationOn) Icons.Filled.NotificationsActive else Icons.Filled.NotificationsOff,
                        contentDescription = null
                    )
                }
                Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                    Text(text = "Atualizar")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeComponentPreview() {
    HomeComponent()
}