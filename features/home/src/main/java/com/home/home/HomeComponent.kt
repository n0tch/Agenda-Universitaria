package com.home.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.core.designsystem.components.ToastComponent
import com.example.model.Note

@Composable
fun HomeComponent(
    navController: NavController,
    onNavigateToNote: (Note) -> Unit,
    onNavigateToNotGraph: () -> Unit,
    onLogout: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("Empty") }
    var photoUrl by remember { mutableStateOf("") }

    when (uiState) {
        is HomeState.HomeCurrentUser -> {
            LaunchedEffect(key1 = name, block = {
                name = (uiState as HomeState.HomeCurrentUser).currentUser.userName
                photoUrl = (uiState as HomeState.HomeCurrentUser).currentUser.photoUrl
            })
        }

        HomeState.HomeIdle -> {}
        is HomeState.HomeCurrentUserError ->
            ToastComponent(message = (uiState as HomeState.HomeCurrentUserError).exception.message.toString())

        is LogoutState.LogoutError ->
            ToastComponent(message = (uiState as LogoutState.LogoutError).exception.message.toString())

        is LogoutState.LogoutSuccess -> {
            LaunchedEffect(key1 = Unit, block = {
                onLogout()
            })
        }
    }

    HomeScaffold(
        name,
        photoUrl,
        content = {
            NoteBodyContent {
//                navController.navigate(NoteScreens.NOTE.route + "/$it")
                onNavigateToNote(it)
            }
        },
        onFabClicked = {
            viewModel.fetchNotes()
//            onNavigateToNotGraph()
//            navController.navigate(noteGraph)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteBodyContent(onNoteClicked: (Note) -> Unit) {
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
                    value = "",
                    onValueChange = {},
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
        CategoryList()
        NotesGrid(onNoteClicked = {onNoteClicked(it) })
    }
}

@Composable
fun CategoryList() {
    LazyRow(modifier = Modifier.padding(start = 8.dp)) {
        items(
            listOf(
                "Prova",
                "Trabalho",
                "Resumo",
                "Prova",
                "Trabalho",
                "Resumo",
                "Prova",
                "Trabalho",
                "Resumo",
                "Prova",
                "Trabalho",
                "Resumo",
            )
        ) {
            CategoryItemView(it, Gray.copy(alpha = .4f))
        }
    }
}

@Composable
fun CategoryItemView(name: String, color: Color) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(color, RoundedCornerShape(6.dp))
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(8.dp),
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScaffold(
    userName: String,
    photoUrl: String,
    content: @Composable () -> Unit,
    onFabClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeHeader(userName = userName, photoUrl = photoUrl)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onFabClicked() }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
            }
        }
    ) {
        Box(Modifier.padding(it)) {
            content()
        }
    }
}
