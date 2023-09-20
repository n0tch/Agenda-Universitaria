package com.features.subject.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.core.designsystem.components.LoadingView

@Composable
fun SubjectContent(
    state: SubjectState,
    onAction: (SubjectListSideEffect) -> Unit = {},
    onSearch: (String) -> Unit = {},
) {
    println("Recomposition")
    var searchVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if(searchVisible){
                SearchTopBar(onSearch = onSearch, onClose = { searchVisible = false })
            } else {
                DefaultTopBar(onBackClicked = { onAction(SubjectListSideEffect.OnBack) }, onSearchClicked = { searchVisible = true })
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(SubjectListSideEffect.NavigateToNewSubject) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(Color.Black)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Organize suas disciplinas aqui!",
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        textAlign = TextAlign.Center
                    )
                }
            }

            SubjectListComponent(
                subjects = state.subjects,
                onCardClicked = { subject ->
                    onAction(SubjectListSideEffect.NavigateToDetail(subject))
                }
            )
        }

        if(state.isLoading){
            LoadingView()
        }
    }
}

@Composable
private fun SearchTopBar(
    onSearch: (String) -> Unit,
    onClose: (String) -> Unit
){
    var searchText by remember { mutableStateOf("") }

    Surface(Modifier.fillMaxWidth()) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearch(it)
            },
            trailingIcon = {
                IconButton(onClick = { onClose(searchText) }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultTopBar(onBackClicked: () -> Unit, onSearchClicked: () -> Unit) {
    TopAppBar(
        modifier = Modifier,
        title = { Text(text = "Disciplinas") },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back subject"
                )
            }
        },
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            }
        }
    )
}

@Preview
@Composable
fun SubjectContentPreview() {
    SubjectContent(SubjectState())
}

@Preview
@Composable
fun SubjectContentLoadingPreview() {
    SubjectContent(SubjectState(isLoading = true))
}
