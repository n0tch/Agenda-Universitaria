package com.core.designsystem.components.chip

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
inline fun <reified T> MultipleChipSelection(
    items: List<T>,
    preSelection: Array<T> = emptyArray(),
    lastItemEnabled: Boolean = false,
    crossinline onLastItemClicked: () -> Unit = {},
    crossinline content: @Composable (T) -> Unit,
    selectedItems: (List<T>) -> Unit
) {
    val selection: SnapshotStateList<T> = remember { mutableStateListOf(*preSelection) }

    FlowRow {
        items.forEach {
            ElevatedFilterChip(
                modifier = Modifier.padding(horizontal = 2.dp),
                label = { content(it) },
                selected = selection.contains(it),
                leadingIcon = {
                    if (selection.contains(it)) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "")
                    }
                },
                onClick = {
                    if (selection.contains(it)) {
                        selection.remove(it)
                    } else {
                        selection.add(it)
                    }
                }
            )
        }

        if (lastItemEnabled) {
            IconButton(onClick = { onLastItemClicked() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    }

    selectedItems(selection)
}