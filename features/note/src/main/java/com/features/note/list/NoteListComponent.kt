package com.features.note.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun NoteListComponent(
    onNavigateToNote: (id: Int) -> Unit,
    onNavigateToNewNote: () -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    val viewModel: NoteListViewModel = hiltViewModel()
    val state by viewModel.collectAsState()
    var showFilterBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNotes()
        viewModel.fetchNoteLabels()
    })

    NoteListScreen(
        state = state,
        onSearchNote = viewModel::searchNote,
        onBackPressed = onBackPressed,
        onNavigateToNewNote = onNavigateToNewNote,
        onDeleteNote = viewModel::deleteNote,
        onNavigateToNote = onNavigateToNote,
        onSearchByLabel = viewModel::searchNotesByLabel,
        onFilterClicked = { showFilterBottomSheet = true }
    )

    viewModel.collectSideEffect {
        when(it){
            NoteListSideEffect.ShowFilter -> showFilterBottomSheet = true
            is NoteListSideEffect.Toast -> {}
        }
    }

    if(showFilterBottomSheet){
        ShowBottomSheet(onDismiss = { showFilterBottomSheet = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheet(onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val coroutine = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismiss()
            coroutine.launch { sheetState.hide() }
        }
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, start = 24.dp)) {
            Text(text = "Filtro Em construcao")
            Text(text = "- Data")
            Text(text = "- Disciplina")

            Button(onClick = {
                coroutine.launch { sheetState.hide() }
                onDismiss()
            }) {
                Text(text = "Filtrar")
            }
        }
    }
}

@Preview
@Composable
fun HomeComponentPreview() {
    NoteListComponent(onNavigateToNote = {})
}