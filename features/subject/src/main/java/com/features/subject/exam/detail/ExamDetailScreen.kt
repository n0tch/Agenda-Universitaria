package com.features.subject.exam.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.LoadingView
import com.core.designsystem.components.alert.BasicAlertDialog
import com.core.designsystem.components.combobox.ComboBox
import com.example.model.Note
import com.example.model.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamDetailScreen(
    isLoading: Boolean,
    subjects: List<Subject>,
    relatedNotes: List<String> = emptyList(),
    onAddNotes: () -> Unit = {},
    examState: ExamState,
    onSaveExam: () -> Unit = {}
) {

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var examTitle by remember { mutableStateOf("") }
    var examScore by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        ComboBox(
            modifier = Modifier.fillMaxWidth(),
            items = subjects.map { it.name },
            onOptionClicked = { subject = it }
        )

        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(2.dp)) {
            items(relatedNotes) {
                Card(
                    modifier = Modifier
                        .width(60.dp)
                        .height(70.dp)
                        .padding(horizontal = 2.dp)
                ) {
                    Text(modifier = Modifier.fillMaxSize(), text = it, textAlign = TextAlign.Center)
                }
            }
            item {
                RelatedNoteButton(onAddNotes = onAddNotes)
            }
        }

        OutlinedButton(onClick = { showDatePicker = true }) {
            Text(text = "Data da prova")
        }

        if (showDatePicker) {
            BasicAlertDialog(onDismiss = { showDatePicker = false }) {
                DatePicker(state = datePickerState)
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = examScore,
            onValueChange = {
                examScore = it
                examState.score = it
            },
            label = { Text("Nota") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = examTitle,
            onValueChange = {
                examTitle = it
                examState.title = it
            },
            label = { Text("Titulo") }
        )

        OutlinedButton(
            onClick = {
                examState.apply {
                    date = datePickerState.selectedDateMillis.toString()
                    this.relatedNotes.addAll(relatedNotes)
                    this.title = title
                    this.score = score
                    this.subject = subject
                }

                onSaveExam()
            }
        ) {
            Text("save exam")
        }
    }

    if(isLoading){
        LoadingView()
    }
}

@Composable
fun RelatedNoteButton(onAddNotes: () -> Unit) {
    Card(
        modifier = Modifier
            .width(60.dp)
            .height(70.dp)
            .clickable { onAddNotes() },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add new note related to exam"
            )
        }
    }
}

@Preview
@Composable
fun ExamDetailScreenPreview() {
    ExamDetailScreen(
        isLoading = false,
        subjects = listOf(),
        relatedNotes = listOf("abc", "edf"),
        onAddNotes = {},
        examState = ExamState()
    )
}

@Preview
@Composable
fun ExamDetailScreenLoadingPreview() {
    ExamDetailScreen(
        isLoading = true,
        subjects = listOf(),
        relatedNotes = listOf("abc", "edf"),
        onAddNotes = {},
        examState = ExamState()
    )
}