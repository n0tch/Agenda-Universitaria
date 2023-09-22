package com.home.list

import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.model.Subject
import com.home.list.navigation.HomeNavigation
import com.home.list.state.HomeSideEffect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeComponent(
    screenList: List<String> = emptyList(),
    onNavigation: (HomeNavigation) -> Unit = {}
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchLatestNotes()
        viewModel.fetchLatestEvents()
    })

    viewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.Toast -> Log.e("Error", it.message)
            is HomeSideEffect.DaySelected -> viewModel.fetchTimetableBtWeekDay(it.dayOfWeek)
        }
    }

    HomeScreen(
        screenList = screenList,
        homeListState = state,
        onAction = { viewModel.setAction(it) },
        onNavigation = { onNavigation(it) },
    )

    if (state.subjectConfigState.showDialog) {
        ShowBottomSheet(state.subjectConfigState.subject) { viewModel.onDismissDialog() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheet(subject: Subject, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val coroutine = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            coroutine.launch { sheetState.hide() }
            onDismiss()
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(subject.name)
            Text(subject.teacher)
            Text(subject.place)
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = if (subject.notificationOn) Icons.Filled.NotificationsActive else Icons.Filled.NotificationsOff,
                    contentDescription = null
                )
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                Text(text = "Atualizar")
            }
        }
    }
}

@Preview
@Composable
fun HomeComponentPreview() {
    HomeComponent()
}