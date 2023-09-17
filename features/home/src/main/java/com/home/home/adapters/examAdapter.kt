package com.home.home.adapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.components.card.CardForward
import com.core.designsystem.extensions.toDayMonthYear
import com.example.model.event.Exam

fun LazyGridScope.examAdapter(
    exams: List<Exam>,
    totalCount: Int = 0,
    onExamClicked: (Exam) -> Unit = {},
    onSeeAll: () -> Unit = {}
){
    item(span = { GridItemSpan(2) }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Proximas provas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                modifier = Modifier.padding(vertical = 8.dp).clickable { onSeeAll() },
                text = "Ver todos(${totalCount})",
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    items(exams, span = { GridItemSpan(1) }) { exam ->
        CardForward(
            title = exam.name,
            body = exam.date.toDayMonthYear(),
            onClick = { onExamClicked(exam) }
        )
    }
}