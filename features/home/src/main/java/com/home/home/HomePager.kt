package com.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.model.Timetable
import com.example.model.TimetableCompound
import java.time.DayOfWeek

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePager(
    timetableMap: Map<DayOfWeek, List<TimetableCompound>> = emptyMap(),
    homePagerContent: @Composable (DayOfWeek, List<TimetableCompound>) -> Unit = { _, _ ->}
) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            pageCount = timetableMap.size,
            state = pagerState,
            beyondBoundsPageCount = 1,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { page ->
            val dayOfWeek = DayOfWeek.of(page + 1)
            homePagerContent(dayOfWeek, timetableMap[dayOfWeek] ?: emptyList())
        }
    }
}

@Preview
@Composable
fun HomePagerPreview() {
    HomePager()
}