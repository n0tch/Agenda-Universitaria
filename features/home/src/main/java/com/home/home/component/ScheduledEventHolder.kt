package com.home.home.component

import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.model.ScheduledEvent
import com.example.model.event.Exam
import java.time.LocalDate

@Composable
fun ScheduledEventHolder(
    date: LocalDate,
    scheduledEvent: ScheduledEvent
) {
    OutlinedCard {
        Text(text = scheduledEvent.id.toString())
        Text(text = scheduledEvent.name)
        if(scheduledEvent is Exam){

        }
    }
}
