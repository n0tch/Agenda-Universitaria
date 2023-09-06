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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.core.designsystem.components.LoadingView
import com.example.model.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectContent(
    subjects: List<Subject> = emptyList(),
    isLoading: Boolean = false,
    onAddSubjectClicked: () -> Unit = {},
    onSubjectDetailClicked: (Subject) -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddSubjectClicked() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
//            FabMenu(
//                iconButton = FabItem(Icons.Filled.Add, "Expand"),
//                items = listOf(
//                    FabItem(Icons.Filled.HomeWork, "Trabalho"),
//                    FabItem(Icons.Filled.Newspaper, "Prova"),
//                ),
//                onFabClicked = {
//
//                },
//            )
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
                subjects = subjects,
                onCardClicked = { subject ->
                    onSubjectDetailClicked(subject)
                }
            )
        }

        if(isLoading){
            LoadingView()
        }
    }
}

@Preview
@Composable
fun SubjectContentPreview() {
    SubjectContent()
}

@Preview
@Composable
fun SubjectContentLoadingPreview() {
    SubjectContent(isLoading = true)
}
