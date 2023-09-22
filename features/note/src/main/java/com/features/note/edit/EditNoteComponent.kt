package com.features.note.edit

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.designsystem.components.LoadingView
import com.core.designsystem.dialogs.CommonSubjectAddDialog
import com.core.designsystem.dialogs.LabelAddDialog
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun EditNoteComponent(
    onBackPressed: () -> Unit,
    noteId: Int,
    isNewNote: Boolean
) {
    val viewModel: EditNoteViewModel = hiltViewModel()

    val state by viewModel.collectAsState()
    var showLabelDialog by remember { mutableStateOf(false) }
    var showSubjectDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNote(noteId)
    })

    if(state.isLoading){
        LoadingView()
    }else{
        NewNoteScreen(
            state = state,
            onSaveClicked = { note, mediaList, labelList ->
                if (isNewNote) {
                    viewModel.saveNote(note, mediaList, labelList)
                } else {
                    viewModel.updateNote(note, mediaList, labelList)
                }
            },
            onBackClicked = onBackPressed,
            addNewLabel = { showLabelDialog = true },
            addSubject = { showSubjectDialog = true }
        )
    }

    if (showLabelDialog) {
        LabelAddDialog(
            onDismiss = { showLabelDialog = false },
            onSaveButtonClicked = {
                viewModel.saveNoteLabel(it)
                showLabelDialog = false
            }
        )
    }

    if (showSubjectDialog) {
        CommonSubjectAddDialog(
            onDismiss = { showSubjectDialog = false },
            onSaveButton = { subject ->
                viewModel.saveSubject(subject.name, subject.place, subject.teacher)
            }
        )
    }

    viewModel.collectSideEffect{
        when(it){
            is EditNoteSideEffect.Toast -> Log.e("toast", it.message)
        }
    }
}