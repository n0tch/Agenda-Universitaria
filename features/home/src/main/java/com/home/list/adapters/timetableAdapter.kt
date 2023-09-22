package com.home.list.adapters

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.components.card.CardConfig
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.Subject
import com.example.model.TimetableCompound
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

fun LazyGridScope.timetableAdapter(
    selectedDay: DayOfWeek,
    timetable: List<TimetableCompound>,
    onTimetableClicked: (Subject) -> Unit = {},
    onConfigClicked: (Subject) -> Unit = {}
) {
    item(span = { GridItemSpan(2) }) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Confira a sua agenda para ${
                selectedDay.getDisplayName(
                    TextStyle.FULL_STANDALONE,
                    Locale.getDefault()
                )
            }",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }

    items(timetable, span = { GridItemSpan(2) }) {
        CardConfig(
            title = it.subject.name,
            body = "${it.timetable.startTime.toMinuteAndSecond()} - ${it.timetable.endTime.toMinuteAndSecond()}",
            onCardClick = { onTimetableClicked(it.subject) },
            onMoreClick = { onConfigClicked(it.subject) }
        )
    }
}
