package com.features.note.newnote

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.core.designsystem.components.alert.BasicAlertDialog
import com.core.designsystem.components.combobox.ComboBox
import com.core.designsystem.components.fab.FabItem
import com.core.designsystem.components.fab.FabMenu
import com.core.designsystem.components.photopicker.imageAndVideoContract
import com.core.designsystem.components.photopicker.multiplePhotoPicker
import com.example.model.Label
import com.example.model.Note
import com.example.model.Subject
import com.features.note.NewNoteButtons
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    note: Note,
    saved: Boolean,
    noteLabels: List<Label>,
    subjects: List<Subject>,
    onSaveClicked: (note: Note, mediaList: List<String>, labels: List<Label>) -> Unit,
    onBackClicked: () -> Unit,
    onSaveLabel: (Label) -> Unit = {}
) {

    val snackBar = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    var displayMenu by remember { mutableStateOf(false) }
    var openLabelDialog by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.body) }
    var subject by remember {
        mutableStateOf(subjects.firstOrNull() ?: Subject(1, "", "", ""))
    }
    var labelText by remember { mutableStateOf("") }
    val photos: MutableList<Uri> = remember { mutableStateListOf() }

    var selectedLabel by remember { mutableStateOf(Label()) }

    val photoPicker = multiplePhotoPicker(onPhotosPicked = { photos.addAll(it) })

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBar) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title.ifEmpty { "Nova Nota" })
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onSaveClicked(
                            Note(
                                title = title,
                                body = description,
                                subjectId = subject.id
                            ),
                            photos.map { it.toString() },
                            listOf(selectedLabel)
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
                        noteLabels.forEach { label ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = CenterVertically) {
                                        Text(
                                            modifier = Modifier.padding(start = 2.dp),
                                            text = label.name
                                        )
                                    }
                                },
                                onClick = {
                                    selectedLabel = label
                                }
                            )
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            FabMenu(
                items = NewNoteButtons.values().map { FabItem(it.icon, it.label) },
                onFabClicked = {
                    when (it.icon) {
                        NewNoteButtons.CAMERA.icon -> {
                            photoPicker.launch(imageAndVideoContract)
                        }
                        NewNoteButtons.TAG.icon -> {
                            openLabelDialog = true
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
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
                initialText = subjects.firstOrNull()?.name ?: "",
                modifier = Modifier.fillMaxWidth(),
                items = subjects.map { it.name },
                onOptionClicked = { subjectName ->
                    subject = subjects.first { subjectName == it.name }
                }
            )

            LazyRow {
                items(photos){
                    AsyncImage(
                        modifier = Modifier.size(128.dp),
                        model = it,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        LaunchedEffect(key1 = saved, block = {
            if (saved) {
                coroutineScope.launch { snackBar.showSnackbar(message = "Saved") }
            }
        })

        if (openLabelDialog) {
            BasicAlertDialog(
                onDismiss = { openLabelDialog = false }
            ) {
                Card(Modifier.padding(4.dp)) {
                    TextField(value = labelText, onValueChange = { labelText = it })
                    Button(onClick = { onSaveLabel(Label(name = labelText)) }) {
                        Text(text = "Salvar")
                    }
                }
            }
        }
    }
}