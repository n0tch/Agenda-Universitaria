package com.core.designsystem.components.colorpicker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.row.GridLazyRow

@Composable
fun ColorPicker(
    title: String,
    onColorSelected: (Color) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    var colorSelected by remember { mutableStateOf(Color.Unspecified) }

    Button(onClick = { showDialog = true }) {
        Text(text = title)
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onColorSelected(colorSelected)
                }) {
                    Text("Selecionar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            },
            tonalElevation = 4.dp,
            title = { Text(title) },
            text = {
                GridLazyRow(
                    list = listOf(
                        Color.Black,
                        Color.Blue,
                        Color.Cyan,
                        Color.DarkGray,
                        Color.Magenta,
                        Color.White,
                        Color.Yellow,
                        Color.LightGray
                    ),
                    itemCount = 4,
                ) {
                    RoundedItemColor(
                        isSelected = it == colorSelected,
                        color = it,
                        onColorSelected = {
                            colorSelected = it
                        })
                }
            }
        )
    }
}

@Preview
@Composable
fun ColorPickerPreview() {
    ColorPicker("Selecione a cor da etiqueta"){

    }
}