package com.features.note.edit

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.core.designsystem.components.chip.MultipleChipSelection
import com.core.designsystem.components.chip.SingleChipSelection
import com.core.designsystem.components.fab.FabMenu
import com.example.model.Label
import com.example.model.Note
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    state: EditNoteState,
    onSaveClicked: (note: Note, mediaList: List<Uri?>, labels: List<Label>) -> Unit,
    onBackClicked: () -> Unit,
    addNewLabel: () -> Unit = {},
    addSubject: () -> Unit = {}
) {

    println("Recomposition")
    val snackBar = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf(state.noteCompound.note.title) }
    var description by remember { mutableStateOf(state.noteCompound.note.body) }
    var subjectId by remember { mutableStateOf(state.noteCompound.note.subjectId) }

    val photos: MutableList<Uri?> = remember { mutableStateListOf() }
    var selectedLabels: List<Label> = remember { mutableStateListOf() }

    val v: MutableList<Uri?> = state.noteCompound.uriPaths.map { Uri.parse(it) }.toMutableList()
    val z = photos.union(v).toList()

    val context = LocalContext.current

    val photoResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                photos.add(it.data!!.data)
            }
        }
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBar) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
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
                                id = state.noteCompound.note.id,
                                title = title,
                                body = description,
                                subjectId = subjectId
                            ),
                            photos,
                            selectedLabels
                        )
                    }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Save Button")
                    }
                },
            )
        },
        floatingActionButton = {
            FabMenu(
                items = EditNoteButtons.values().map { it },
                onFabClicked = {
                    when (it.icon) {
                        EditNoteButtons.GALLERY.icon -> {
                            ImagePicker
                                .with(context as Activity)
                                .galleryOnly()
                                .crop()
                                .createIntent { intent -> photoResult.launch(intent) }
                        }

                        EditNoteButtons.CAMERA.icon -> {
                            ImagePicker
                                .with(context as Activity)
                                .cameraOnly()    //User can only capture image using Camera
                                .crop()
                                .createIntent { intent -> photoResult.launch(intent) }
                        }

                        EditNoteButtons.TAG.icon -> {
                            addNewLabel()
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("Titulo da nota") }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Descricao da nota") }
            )

            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Text("Selecione a(s) etiqueta")
                MultipleChipSelection(
                    items = state.labels,
                    preSelection = state.noteCompound.labels.toTypedArray(),
                    content = {
                        Text(text = it.name)
                    },
                    selectedItems = {
                        selectedLabels = it
                    },
                    lastItemEnabled = true,
                    onLastItemClicked = {
                        addNewLabel()
                    }
                )
            }

            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Text("Selecione a materia")
                SingleChipSelection(
                    items = state.subjects,
                    preSelected = { state.noteCompound.subject },
                    content = { Text(text = it.name) },
                    onSelection = { subjectId = it.id },
                    isLastItemSelected = true,
                    onLastItemClicked = { addSubject() }
                )
            }

            LazyRow {
                items(z) {
                    Log.e("photo", it?.path.toString())
                    AsyncImage(
                        modifier = Modifier.size(128.dp),
                        model = Uri.parse(it.toString()),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        LaunchedEffect(key1 = state.noteSaved, block = {
            if (state.noteSaved) {
                coroutineScope.launch { snackBar.showSnackbar(message = "Saved") }
            }
        })
    }
}




