package com.core.designsystem.components.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class DefaultItemEnum(
    override val icon: ImageVector,
    override val label: String
): FabItemEnum{
    ADD(Icons.Filled.Add, "More")
}

@Composable
fun FabMenu(
    iconButton: FabItemEnum = DefaultItemEnum.ADD,
    items: List<FabItemEnum>,
    onFabClicked: (FabItemEnum) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

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
                        Spacer(Modifier.width(2.dp))
                        FloatingActionButton(
                            modifier = Modifier.padding(bottom = 2.dp),
                            onClick = {
                                expanded = expanded.not()
                                onFabClicked.invoke(fabItem)
                            }
                        ) {
                            Icon(imageVector = fabItem.icon, contentDescription = "icons")
                        }
                    }
                }
            }
        }
        FloatingActionButton(onClick = {
            expanded = expanded.not()
        }) {
            Icon(
                imageVector = iconButton.icon,
                contentDescription = "icons"
            )
        }
    }
}
