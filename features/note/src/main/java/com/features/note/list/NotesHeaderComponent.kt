package com.features.note.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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

@Composable
fun HomeNotesHeaderComponent(
    onSearch: (String) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (backRef, textField, searchIcon) = createRefs()

                IconButton(
                    modifier = Modifier.constrainAs(backRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                    onClick = { onBackPressed() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }

                OutlinedTextField(
                    modifier = Modifier
                        .constrainAs(textField) {
                            top.linkTo(parent.top)
                            start.linkTo(backRef.end)
                            end.linkTo(searchIcon.start)
                            width = Dimension.fillToConstraints
                        }
                        .padding(horizontal = 4.dp),
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