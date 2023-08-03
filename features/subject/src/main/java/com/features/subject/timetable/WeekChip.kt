package com.features.subject.timetable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WeekChip(
    text: String,
    selected: Boolean = false,
    onClick: (text: String, selected: Boolean) -> Unit = { _, _ -> }
) {

    var isSelected by remember { mutableStateOf(selected) }

    AssistChip(
        onClick = {
            isSelected = isSelected.not()
            onClick(text, isSelected)
        },
        label = { Text(text) },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = ""
                )
            }
        }
    )
}

@Preview
@Composable
fun WeekChipSelectedPreview() {
    WeekChip(text = "Segunda", selected = true)
}

@Preview
@Composable
fun WeekChipDeselectedPreview() {
    WeekChip(text = "Terça", selected = false)
}