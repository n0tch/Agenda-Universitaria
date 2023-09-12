package com.feature.exam.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.LoadingView
import com.core.designsystem.components.card.CardForward
import com.core.designsystem.components.schedule.EventScheduleItem
import com.core.designsystem.extensions.toDayMonthYear
import com.example.model.event.Exam

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamScreen(
    isLoading: Boolean = false,
    exams: List<Exam> = listOf(),
    onFabClicked: (String?) -> Unit,
    onBackClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Provas") },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onFabClicked(null) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Timetable entry")
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(exams) { exam ->
                        EventScheduleItem(exam.date.toDayMonthYear()) {
                            Text(exam.name)
                        }
                    }
                }
            )
        }

        if (isLoading) {
            LoadingView()
        }
    }
}

@Preview
@Composable
fun ExamScreenPreview() {
    ExamScreen(
        exams = listOf(Exam.getMock()),
        onFabClicked = {},
        onBackClicked = {}
    )
}

@Preview
@Composable
fun ExamScreenLoadingPreview() {
    ExamScreen(
        isLoading = true,
        exams = listOf(Exam.getMock()),
        onFabClicked = {},
        onBackClicked = {}
    )
}