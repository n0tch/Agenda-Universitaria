package com.core.designsystem.components.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun FabMenu(
    baseButton: FabItem = FabItem(Icons.Filled.ExpandMore, "More"),
    items: List<FabItem>,
    onFabClicked: (FabItem) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val rotation = remember { Animatable(0f) }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            LazyColumn(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.End) {
                itemsIndexed(items) { _, fabItem ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {

                        Text(text = fabItem.label)

                        FloatingActionButton(
                            modifier = Modifier.padding(bottom = 2.dp),
                            onClick = { onFabClicked(fabItem) }) {
                            Icon(imageVector = fabItem.icon, contentDescription = "icons")
                        }
                    }
                }
            }
        }
        FloatingActionButton(onClick = {
            expanded = !expanded
            coroutineScope.launch {
                rotation.animateTo(targetValue = if(expanded) 360f else 180f)
            }
        }) {
            Icon(modifier = Modifier.rotate(rotation.value), imageVector = baseButton.icon, contentDescription = "icons")
        }
    }
}

@Preview
@Composable
fun FabMenuPreview() {
    FabMenu(
        baseButton = FabItem(Icons.Filled.ExpandMore, "More"),
        items = listOf(
            FabItem(Icons.Filled.CameraAlt, "Camera"),
            FabItem(Icons.Filled.Photo, "Galeria")
        ),
        onFabClicked = {})
}