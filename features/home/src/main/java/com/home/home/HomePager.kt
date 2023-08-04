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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePager(
    count: Int,
    homePagerContent: @Composable () -> Unit = {}
) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            pageCount = count,
            state = pagerState,
            beyondBoundsPageCount = 1,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            homePagerContent()
        }
    }
}

@Preview
@Composable
fun HomePagerPreview() {
    HomePager(count = 1)
}