package com.features.note.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.model.Note

@Composable
fun HomeNotesHeaderComponent(
    onSearch: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (textField, searchIcon) = createRefs()

                TextField(modifier =
                Modifier
                    .wrapContentWidth()
                    .fillMaxWidth(1f)
                    .constrainAs(textField) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(searchIcon.start)
                        width = Dimension.preferredWrapContent
                    }
                    .padding(8.dp),
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearch(searchText)
                    },
                    placeholder = {
                        Text(text = "Buscar notas")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    })

                IconButton(
                    modifier = Modifier
                        .fillMaxWidth(.1f)
                        .constrainAs(searchIcon) {
                            end.linkTo(parent.end, margin = 4.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.FilterList, contentDescription = "")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeNotesComponentPreview() {
    HomeNotesHeaderComponent()
}