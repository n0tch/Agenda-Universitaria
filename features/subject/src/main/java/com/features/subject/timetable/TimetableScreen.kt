package com.features.subject.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.TimetableEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen(
    onBack: () -> Unit,
    onNewTimetable: () -> Unit = {}
) {

    val viewModel: TimetableViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var timetableEntries: List<TimetableEntry> = remember {
        mutableStateListOf()
    }

    when(uiState){
        is TimetableState.Entries -> { timetableEntries = (uiState as TimetableState.Entries).items }
        TimetableState.Idle -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Grade Horaria") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNewTimetable() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Timetable entry")
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TimeTableComponent(timetableEntries = timetableEntries)
        }
    }
}