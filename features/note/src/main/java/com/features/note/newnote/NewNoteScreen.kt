package com.features.note.newnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.combobox.ComboBox
import com.core.designsystem.components.fab.FabMenu
import com.example.model.Note
import com.example.model.Subject
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    note: Note,
    saved: Boolean,
    noteLabels: List<DefaultNoteEnum>,
    subjects: List<Subject>,
    onSaveClicked: (String, String, String, String) -> Unit,
    onBackClicked: () -> Unit
) {

    val context = LocalContext.current
    val snackBar = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    var displayMenu by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.body) }
    var subject by remember { mutableStateOf(note.subject) }

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
                    IconButton(onClick = {
                        onSaveClicked(
                            title,
                            description,
                            context.getString(noteLabels.first { it.isSelected }.label),
                            subject
                        )
                    }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Save Button")
                    }
                    IconButton(onClick = { displayMenu = !displayMenu }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "more options"
                        )
                    }
                    DropdownMenu(
                        expanded = displayMenu,
                        onDismissRequest = { displayMenu = false }) {
                        noteLabels.forEach { noteEnum ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = noteEnum.icon),
                                            contentDescription = "sajisa"
                                        )
                                        Text(
                                            modifier = Modifier.padding(start = 2.dp),
                                            text = stringResource(id = noteEnum.label)
                                        )
                                    }
                                },
                                onClick = {
                                    noteLabels.map {
                                        it.apply { isSelected = false }
                                    }.first {
                                        it == noteEnum
                                    }.isSelected = true
                                }
                            )
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
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it }
            )
            ComboBox(
                initialText = subject,
                modifier = Modifier.fillMaxWidth(),
                items = subjects.map { it.name },
                onOptionClicked = {
                    subject = it
                }
            )
        }

        LaunchedEffect(key1 = saved, block = {
            if (saved) {
                coroutineScope.launch { snackBar.showSnackbar(message = "Saved") }
            }
        })
    }
}