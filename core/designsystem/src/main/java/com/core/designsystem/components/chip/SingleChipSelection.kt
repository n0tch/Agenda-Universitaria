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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T> SingleChipSelection(
    items: List<T>,
    preSelected: () -> T? = { null },
    isLastItemSelected: Boolean = false,
    onLastItemClicked: () -> Unit = {},
    content: @Composable (T) -> Unit,
    onSelection: (T) -> Unit = {}
) {
    var itemSelected: T? by remember { mutableStateOf(preSelected()) }

    FlowRow {
        items.forEach {
            ElevatedFilterChip(
                modifier = Modifier.padding(horizontal = 2.dp),
                label = { it?.let { content(it) } },
                leadingIcon = {
                    if (itemSelected == it) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "")
                    }
                },
                onClick = {
                    itemSelected = if (itemSelected == it)
                        null
                    else {
                        onSelection(it)
                        it
                    }
                },
                selected = itemSelected == it
            )
        }

        if(isLastItemSelected){
            IconButton(onClick = { onLastItemClicked() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}