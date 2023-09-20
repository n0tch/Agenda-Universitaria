package com.features.subject.edit

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SubjectEditComponent() {

    val viewModel: SubjectEditViewModel = hiltViewModel()
    val state by viewModel.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    SubjectEditScreen(
        state = state,
        onAction = { viewModel.saveSubject(it) }
    )

    viewModel.collectSideEffect{
        when(it){
            SubjectEditSideEffect.SubjectSaved -> showDialog = true
            is SubjectEditSideEffect.Toast -> {}
        }
    }

    if(showDialog){
        AlertDialog(title = { Text("Subject saved!") }, confirmButton = { showDialog = false }, onDismissRequest = { showDialog = false})
    }
}