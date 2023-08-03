package com.features.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.model.Subject

@Composable
fun SubjectAddDialog(
    onSaveButton: (Subject) -> Unit,
    onDismiss: () -> Unit
) {

    var subjectName by remember { mutableStateOf("") }
    var placeName by remember { mutableStateOf("") }
    var teacherName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                Modifier
                    .background(Color.White)
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
                                Subject(
                                    name = subjectName,
                                    place = placeName,
                                    teacher = teacherName
                                )
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
fun SubjectAddDialogPreview() {
    SubjectAddDialog(onSaveButton = { }, onDismiss = {})
}