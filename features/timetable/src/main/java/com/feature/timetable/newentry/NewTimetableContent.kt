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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.dialogs.CommonSubjectAddDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTimeTableContent(
    onBackPressed: () -> Unit = {}
) {
    val viewModel: NewTimetableViewModel = hiltViewModel()
    val subjects by viewModel.subjects.collectAsStateWithLifecycle()

    var showAddSubjectDialog by remember { mutableStateOf(false) }

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
                onSaveButtonClicked = viewModel::saveTimetables,
                onAddNewSubjectClicked = {
                    showAddSubjectDialog = true
                }
            )
        }

        if(showAddSubjectDialog){
            CommonSubjectAddDialog(
                onDismiss = {
                    showAddSubjectDialog = false
                },
                onSaveButton = { name, place, teacher ->
                    viewModel.saveSubject(name, place, teacher)
                    showAddSubjectDialog = false
                }
            )
        }
    }
}

@Preview
@Composable
fun NewTimeTableContentPreview() {
    NewTimeTableContent(){}
}