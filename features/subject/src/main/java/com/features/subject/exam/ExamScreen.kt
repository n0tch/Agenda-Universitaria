package com.features.subject.exam

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
import com.core.designsystem.components.card.CardForward
import com.example.model.Exam
import com.features.subject.timetable.TimeTableComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamScreen(
    exams: List<Exam> = listOf(Exam("Prova 1", 10L, ""), Exam("Prova 1", 10L, ""))
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Provas") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Timetable entry")
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(), content = {
                items(exams) { exam ->
                    CardForward(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                            .padding(horizontal = 6.dp),
                        title = exam.name,
                        body = "",
                        onClick = {}
                    )
                }
            })
        }
    }
}

@Preview
@Composable
fun ExamScreenPreview() {
    ExamScreen()
}