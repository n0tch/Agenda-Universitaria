package com.features.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import com.core.designsystem.components.fab.FabMenu
import com.example.model.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    note: Note,
    saved: Boolean,
    noteLabels: List<String>,
    onSaveClicked: (String, String, String) -> Unit,
    onBackClicked: () -> Unit
) {

    val snackBar = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    var displayMenu by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBar) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = note.title.ifEmpty { "Nova Nota" })
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = { onSaveClicked(title, description, label) }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Save Button")
                    }
                    IconButton(onClick = { displayMenu = !displayMenu }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "more options"
                        )
                    }
                    DropdownMenu(expanded = displayMenu, onDismissRequest = { displayMenu = false }) {
                        noteLabels.forEach {
                            DropdownMenuItem(text = { Text(it.lowercase().capitalize()) }, onClick = { label = it })
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            FabMenu(
                items = listOf(),
                onFabClicked = {}
            )
        },
        bottomBar = {}
    ) { padding ->
        Column(Modifier.padding(padding)) {
            OutlinedTextField(value = title, onValueChange = { title = it })
            OutlinedTextField(value = description, onValueChange = { description = it })
        }

        LaunchedEffect(key1 = saved, block = {
            if (saved) {
                coroutineScope.launch { snackBar.showSnackbar(message = "Saved") }
            }
        })
    }
}