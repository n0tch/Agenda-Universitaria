package com.core.data.extension

import com.core.network.model.noteResponse.NoteLabelResponse
import com.example.model.NoteLabel

fun NoteLabelResponse.toNoteLabel() = NoteLabel(
    labelId = "",
    labelName = this.labelName ?: ""
)