package com.core.designsystem.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.alert.BasicAlertDialog

@Composable
fun LabelAddDialog(
    onDismiss: () -> Unit = {},
    onSaveButtonClicked: (label: String) -> Unit = {}
) {
    var label by remember {
        mutableStateOf("")
    }
    BasicAlertDialog(
        onDismiss = { onDismiss() }
    ) {
        Card(
            Modifier
                .padding(4.dp)
                .border(2.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Add new Label")

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    value = label,
                    onValueChange = { label = it }
                )

                Row {
                    OutlinedButton(onClick = { onDismiss() }) {
                        Text(text = "Cancelar")
                    }
                    Button(onClick = { onSaveButtonClicked(label) }) {
                        Text(text = "Salvar")
                    }
                }
            }
        }
    }
}