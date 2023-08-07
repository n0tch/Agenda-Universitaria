package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TimetableEntry

@Composable
fun TimetableListComponent(
    timetableEntries: List<TimetableEntry> = emptyList()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                text = "Hoje, segunda feira dia 26/10/2023",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            LazyColumn {
                items(timetableEntries) {
                    Text(text = it.startTime)
                    Text(text = it.endTime)
                    Text(text = it.subjectId)
                    Spacer(Modifier.height(4.dp))
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun TimetableListComponentPreview() {
    TimetableListComponent(
        timetableEntries = listOf(
            TimetableEntry(
                id = "",
                weekDays = listOf("segunda", "terca"),
                startTime = "10:00",
                endTime = "12:00",
                subjectId = "Direito"
            ),
            TimetableEntry(
                id = "",
                weekDays = listOf("segunda", "terca"),
                startTime = "13:00",
                endTime = "15:00",
                subjectId = "Filosofia"
            )
        )
    )
}