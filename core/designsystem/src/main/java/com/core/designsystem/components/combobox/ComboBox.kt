package com.core.designsystem.components.combobox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComboBox(
    modifier: Modifier = Modifier,
    items: List<String>,
    onOptionClicked: (String) -> Unit,
    initialText: String = "Selecione alguma opção"
) {

    var expanded by remember { mutableStateOf(false) }
    var textSelected by remember { mutableStateOf(initialText) }

    Column(modifier) {

        OutlinedTextField(
            modifier = Modifier,
            value = textSelected,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = {
                        Text(it)
                    },
                    onClick = {
                        textSelected = it
                        expanded = false
                        onOptionClicked(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ComboBoxPreview() {
    ComboBox(items = listOf("Abc", "EFG"), onOptionClicked = {})
}
