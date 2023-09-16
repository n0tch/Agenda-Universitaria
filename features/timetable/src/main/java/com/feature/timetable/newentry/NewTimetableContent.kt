package com.feature.timetable.newentry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTimeTableContent(onBackPressed: () -> Unit) {
    val viewModel: NewTimetableViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var subjects: List<Subject> = remember { mutableStateListOf() }

    when (uiState) {
        NewTimetableState.Idle -> {}
        is NewTimetableState.Subjects -> {
            subjects = (uiState as NewTimetableState.Subjects).subjects
        }
    }

    Scaffold(
        topBar = { 
            TopAppBar(
                title = { 
                    Text("Adicionar Grade") 
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            NewTimetableScreen(
                subjects = subjects,
                onSaveButtonClicked = viewModel::saveTimetables
            )
        }
    }
}

@Preview
@Composable
fun NewTimeTableContentPreview() {
    NewTimeTableContent(){}
}