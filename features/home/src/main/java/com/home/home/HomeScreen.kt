package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Hexagon
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.card.CardForward
import com.core.designsystem.components.fab.FabItem
import com.core.designsystem.components.fab.FabMenu
import com.core.designsystem.extensions.toDayMonthYear
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.Subject
import com.example.model.TimetableCompound
import com.example.model.event.Exam
import com.home.home.component.HomeProfileHeader
import com.home.home.component.WeeklyDaySelector
import com.home.home.drawer.DrawerBody
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(
    screenList: List<String>,
    nextExams: List<Exam>,
    timetable: List<TimetableCompound>,
    today: DayOfWeek,
    addScreens: List<String> = emptyList(),
    onNavigateToScreen: (String) -> Unit = {},
    onWeekDaySelected: (DayOfWeek) -> Unit = {},
    onTimetableClicked: (Subject) -> Unit = {},
    onAddScreen: (String) -> Unit = {}
) {
    var selectedDay by rememberSaveable { mutableStateOf(today) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val isGestureEnabled = drawerState.isOpen
    val coroutine = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = isGestureEnabled,
        drawerContent = {
            DrawerBody(
                photoUrl = "",
                screensList = screenList,
                onDrawerItemClicked = onNavigateToScreen
            )
        },
        content = {
            Scaffold(
                modifier = Modifier,
                topBar = {
                    Column {
                        HomeProfileHeader(
                            onProfileClicked = {
                                coroutine.launch { drawerState.open() }
                            }
                        )
                        WeeklyDaySelector(
                            onClick = {
                                selectedDay = it
                                onWeekDaySelected(it)
                            }
                        )
                    }
                },
                floatingActionButton = {
                    FabMenu(
                        items = listOf(
                            FabItem(Icons.Filled.Note, "Add note"),
                            FabItem(Icons.Filled.Newspaper, "Add disciplina"),
                            FabItem(Icons.Filled.CalendarMonth, "Add Timetable"),
                            FabItem(Icons.Filled.Hexagon, "Add exam")
                        ),
                        onFabClicked = {
                            val addScreenRoute = when(it.icon){
                                Icons.Filled.Note -> ""
                                else -> ""
                            }

                            onAddScreen(addScreenRoute)
                        }
                    )
                },
            ) {
                LazyColumn(
                    Modifier
                        .padding(it)
                        .padding(horizontal = 8.dp)
                ) {
                    item {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Confira a sua agenda para ${
                                selectedDay.getDisplayName(
                                    TextStyle.FULL_STANDALONE,
                                    Locale.getDefault()
                                )
                            }",
                            textAlign = TextAlign.Center
                        )
                    }

                    if (timetable.isEmpty()) {
                        item {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Sem eventos"
                            )
                        }
                    } else {
                        items(timetable) {
                            CardForward(
                                title = it.subject.name,
                                body = "${it.timetable.startTime.toMinuteAndSecond()} - ${it.timetable.endTime.toMinuteAndSecond()}"
                            ) {
                                onTimetableClicked(it.subject)
                            }
                        }
                    }

                    item {
                        Text(text = "Proximas provas")
                    }

                    items(nextExams) { exam ->
                        CardForward(
                            title = exam.name,
                            body = exam.date.toDayMonthYear(),
                            onClick = {})
                    }
                }
            }
        }
    )
}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        screenList = listOf(),
//        nextExams = listOf(),
//        timetable = listOf(),
//        today = LocalDate.now().dayOfWeek
//    )
//}