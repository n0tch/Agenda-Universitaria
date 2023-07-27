package com.features.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import com.core.designsystem.components.fab.FabMenu
import com.example.model.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    note: Note,
    saved: Boolean,
    onSaveClicked: (String, String) -> Unit,
    onBackClicked: () -> Unit
) {

    val snackBar = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
                    IconButton(onClick = { onSaveClicked(title, description) }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Save Button")
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