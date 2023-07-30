package com.core.designsystem.components.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun <T>GridLazyRow(
    itemCount: Int = 2,
    list: List<T>,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    verticalArrangementPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(8.dp),
    horizontalArrangementPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(8.dp),
    content: @Composable (T) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(itemCount),
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangementPadding,
        horizontalArrangement = horizontalArrangementPadding
    ) {
        items(list) {
            content(it)
        }
    }
}