package com.home.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.core.designsystem.components.ToastComponent
import com.core.designsystem.components.drawer.BasicDrawer
import com.core.designsystem.components.row.PillLazyRow
import com.example.model.Note
import com.home.home.drawer.DrawerBody
import kotlinx.coroutines.launch

@Composable
fun HomeComponent(
    onNavigateToNote: (Note) -> Unit,
    onNavigateToNotGraph: () -> Unit,
    onLogout: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("Empty") }
    var photoUrl by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

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

    BasicDrawer(
        drawerState = drawerState,
        drawerContent = { /*TODO*/ },
        screenContent = {  }
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier) {
                DrawerBody(
                    photoUrl = photoUrl,
                    screensList = listOf("Notas", "Trabalhos", "Provas")
                ) {
                    //TODO: Navigate to screen
                }
            }
        },
        content = {
            Scaffold(
                modifier = Modifier,
                topBar = {
                    HomeHeader(
                        userName = name,
                        photoUrl = photoUrl,
                        onProfileClick = {
                            if (drawerState.isOpen) {
                                coroutine.launch { drawerState.close() }
                            } else {
                                coroutine.launch { drawerState.open() }
                            }
                        }
                    )
                },

                floatingActionButton = {
                    FloatingActionButton(onClick = { viewModel.fetchNotes() }) {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
                    }
                }
            ) {
                Column(Modifier.padding(it)) {
                    HomeNotesComponent(onNoteClicked = { note -> onNavigateToNote(note) })
                    PillLazyRow(
                        pillList = listOf(
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
                    )
                    NotesGrid(onNoteClicked = { note -> onNavigateToNote(note) })
                }
            }
        })
}

@Preview
@Composable
fun HomeComponentPreview() {
    HomeComponent(onNavigateToNote = {}, onNavigateToNotGraph = { }, onLogout = {})
}