package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.TimetableEntry

@Composable
fun HomeScreen(
    name: String,
    photoUrl: String,
    timetable: List<TimetableEntry>,
    onProfileClick: () -> Unit,
    onFloatingActionButtonClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            HomeHeader(
                userName = name,
                photoUrl = photoUrl,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFloatingActionButtonClicked) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Confira a sua agenda",
                textAlign = TextAlign.Center
            )

            HomePager(count = timetable.size,homePagerContent = {
                TimetableListComponent(timetableEntries = timetable)
            })

//            UpcomingEventsList()
            //"Confira a sua agenda para amanha, terça feira dia 26/10/2023
            //"Confira a sua agenda para quarta feira  dia 26/10/2023

            //Cards com as aulas do dia -> clicando vai para o detalhe da disciplina

            //provas (do dia || proximos eventos) slide automatically
            //trabalhos (do dia || proximos eventos) slide automatically
            //notificações
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        name = "my name",
        photoUrl = "",
        timetable = listOf(),
        onProfileClick = {},
        onFloatingActionButtonClicked = {})
}