package com.features.note.newnote

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.features.note.R

enum class DefaultNoteEnum(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    var isSelected: Boolean = false
) {
    SUMMARY(R.string.note_label_resume, R.drawable.baseline_subject),
    EXAM(R.string.note_label_exam, R.drawable.baseline_edit_note),
    HOMEWORK(R.string.note_label_homework, R.drawable.baseline_maps_home_work);
}
