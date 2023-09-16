package com.features.note.detail

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.util.DebugLogger
import com.core.designsystem.components.alert.BasicAlertDialog
import com.core.designsystem.components.clickableNoRipple
import com.core.designsystem.components.row.PillItem
import com.core.designsystem.zoom.ZoomableImage
import com.example.model.NoteCompound
import com.features.note.R
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    noteCompound: NoteCompound,
    onBackPressed: () -> Unit = {},
    onEditNotePressed: (Int) -> Unit = {}
) {
    var selectedImage: Uri? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEditNotePressed(noteCompound.note.id) }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit note")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(noteCompound.note.title)
                Text(text = noteCompound.note.body)
            }

            LazyRow{
                items(noteCompound.labels){
                    PillItem(name = it.name, color = Color.White)
                }
            }

            OutlinedCard(
                Modifier
                    .clickableNoRipple { }
                    .fillMaxWidth()) {
                Text(text = noteCompound.subject.name)
                Text(text = noteCompound.subject.place)
                Text(text = noteCompound.subject.teacher)
            }

            LazyRow {
                items(noteCompound.uriPaths.map { uri -> Uri.parse(uri) }) { photo ->
                    AsyncImage(
                        modifier = Modifier
                            .size(120.dp)
                            .clickableNoRipple {
                                selectedImage = photo
                            },
                        model = photo,
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        onError = { error ->
                            Log.e("error", error.result.throwable.message.toString())
                        },
                        error = painterResource(id = R.drawable.baseline_edit_note)
                    )
                }
            }
        }
    }

    selectedImage?.let {
        ZoomableImage(uri = it) {
            selectedImage = null
        }
    }
}