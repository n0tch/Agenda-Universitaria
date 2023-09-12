package com.features.note.edit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Photo
import androidx.compose.ui.graphics.vector.ImageVector

enum class EditNoteButtons(val icon: ImageVector, val label: String) {
    CAMERA(Icons.Filled.CameraAlt, "Foto"),
    GALLERY(Icons.Filled.Photo, "Galeria"),
    TAG(Icons.Filled.Label, "Etiqueta"),
    AUDIO(Icons.Filled.Mic, "Audio"),
    REMEMBER(Icons.Filled.CalendarMonth, "Lembrar")
}