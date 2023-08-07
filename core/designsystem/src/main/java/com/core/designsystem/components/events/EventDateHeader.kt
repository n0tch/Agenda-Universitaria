package com.core.designsystem.components.events

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.util.Locale
import androidx.compose.ui.text.TextStyle

@Composable
fun EventDateHeader(date: LocalDate) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${date.dayOfMonth}",
                style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize)
            )
            Text(
                text = date
                    .dayOfWeek.toString().take(3)
                    .replaceFirstChar { it.uppercase() },
                style = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = date.month.name.lowercase().replaceFirstChar { it.uppercase() },
                style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize)
            )
            Text(
                text = "${date.year}",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                style = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EventDateHeaderPreview() {
    EventDateHeader(date = LocalDate.now())
}