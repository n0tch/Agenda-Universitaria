package com.core.designsystem.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CommonSubjectAddDialog(
    onSaveButton: (name: String, place: String, teacher: String) -> Unit,
    onDismiss: () -> Unit
) {

    var subjectName by remember { mutableStateOf("") }
    var placeName by remember { mutableStateOf("") }
    var teacherName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Add new discipline",
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    value = subjectName,
                    singleLine = true,
                    onValueChange = { subjectName = it },
                    placeholder = { Text(text = "Nome da Disciplina") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    value = placeName,
                    singleLine = true,
                    onValueChange = { placeName = it },
                    placeholder = { Text(text = "Sala") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    value = teacherName,
                    singleLine = true,
                    onValueChange = { teacherName = it },
                    placeholder = { Text(text = "Professor") }
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(onClick = { onDismiss() }) {
                        Text(text = "Cancelar")
                    }
                    Button(
                        onClick = {
                            onSaveButton(
                                subjectName,
                                placeName,
                                teacherName
                            )
                        }
                    ) {
                        Text(text = "Salvar")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CommonSubjectAddDialogPreview() {
    CommonSubjectAddDialog(onSaveButton = {_,_,_ -> }, onDismiss = {})
}