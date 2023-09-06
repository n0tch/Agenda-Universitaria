package com.features.note

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Mic
import androidx.compose.ui.graphics.vector.ImageVector

enum class NewNoteButtons(val icon: ImageVector, val label: String) {
    CAMERA(Icons.Filled.CameraAlt, "Foto"),
    TAG(Icons.Filled.Label, "Etiqueta"),
    AUDIO(Icons.Filled.Mic, "Audio"),
    REMEMBER(Icons.Filled.CalendarMonth, "Lembrar")
}