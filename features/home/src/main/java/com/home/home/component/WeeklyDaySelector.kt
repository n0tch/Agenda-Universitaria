package com.home.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeeklyDaySelector(
    onClick: (DayOfWeek) -> Unit = {}
) {
    val days = DayOfWeek.values()
    var daySelected by remember { mutableStateOf(LocalDate.now().dayOfWeek) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
            .background(MaterialTheme.colorScheme.inversePrimary)
            .padding(horizontal = 12.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            days.forEach {
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (daySelected == it) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.background)
                        .size(32.dp)
                        .wrapContentSize()
                        .clickable {
                            daySelected = it
                            onClick(it)
                        },
                    text = it.getDisplayName(TextStyle.SHORT, Locale.getDefault()).first().uppercase(),
                    textAlign = TextAlign.Center,
                    color = if (daySelected == it) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun WeeklyDaySelectorPreview() {
    WeeklyDaySelector()
}