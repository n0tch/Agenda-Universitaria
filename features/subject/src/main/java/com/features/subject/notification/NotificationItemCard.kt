package com.features.subject.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NotificationItemCard() {
    var checked by remember{ mutableStateOf(true) }
    Card(modifier = Modifier.clickable {  }) {
        Column(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Title: Work", fontSize = MaterialTheme.typography.titleSmall.fontSize)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "8:30", fontSize = MaterialTheme.typography.displaySmall.fontSize)
                Spacer(Modifier.width(2.dp))
                Text(text = "AM", fontSize = MaterialTheme.typography.titleSmall.fontSize)
            }
            Text(text = "Dia 2 de Janeiro", fontSize = MaterialTheme.typography.titleSmall.fontSize)

            Row(horizontalArrangement = Arrangement.End) {
                Switch(modifier = Modifier,checked = checked, onCheckedChange = { checked = checked.not() })
            }
        }
    }
}

@Preview
@Composable
fun NotificationItemCardPreview() {
    NotificationItemCard()
}