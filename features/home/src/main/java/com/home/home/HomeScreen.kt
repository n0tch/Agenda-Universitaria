package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.core.designsystem.components.card.CardForward
import com.core.designsystem.extensions.toDayMonthYear
import com.example.model.Timetable
import com.example.model.TimetableCompound
import com.example.model.event.Exam
import java.time.DayOfWeek

@Composable
fun HomeScreen(
    currentUserState: CurrentUserState,
    nextExams: List<Exam>,
    timetable: Map<DayOfWeek, List<TimetableCompound>>,
    onProfileClick: () -> Unit,
    onFloatingActionButtonClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            HomeHeader(
                userName = currentUserState.username,
                photoUrl = currentUserState.photoUrl,
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

            HomePager(timetableMap = timetable, homePagerContent = { dayOfWeek, entries ->
                TimetableListComponent(dayOfWeek = dayOfWeek, timetableEntries = entries)
            })

            LazyColumn{

                item{
                    Text(text = "Proximas provas")
                }

                items(nextExams){ exam ->
                    CardForward(title = exam.name, body = exam.date.toDayMonthYear(), onClick = {})
                }
            }
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
        currentUserState = CurrentUserState(),
        nextExams = listOf(),
        timetable = mapOf(),
        onProfileClick = {},
        onFloatingActionButtonClicked = {})
}