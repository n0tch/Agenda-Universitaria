package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Hexagon
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.fab.FabItem
import com.core.designsystem.components.fab.FabMenu
import com.home.home.adapters.examAdapter
import com.home.home.adapters.notesAdapter
import com.home.home.adapters.timetableAdapter
import com.home.home.component.HomeProfileHeader
import com.home.home.component.WeeklyDaySelector
import com.home.home.drawer.DrawerBody
import com.home.home.navigation.HomeNavigation
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    screenList: List<String>,
    examsState: ExamsState,
    timetableState: TimetableState,
    notesState: NoteState,
    onAction: (HomeActon) -> Unit = {},
    onNavigation: (HomeNavigation) -> Unit = {}
) {
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
                onDrawerItemClicked = { onNavigation(HomeNavigation.NavigateToScreenByName(it)) }
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
                            selectedDay = timetableState.selectedDayOfWeek,
                            onClick = {
                                onAction(HomeActon.DaySelected(it))
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
                        onFabClicked = {}
                    )
                },
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 12.dp),
                    columns = GridCells.Fixed(2),
                ) {
                    timetableAdapter(
                        selectedDay = timetableState.selectedDayOfWeek,
                        timetable = timetableState.timetables,
                        onTimetableClicked = { subject -> onAction(HomeActon.TimeTableConfig(subject)) }
                    )
                    examAdapter(
                        exams = examsState.nextExams,
                        totalCount = examsState.totalCount,
                        onExamClicked = { exam -> onAction(HomeActon.ExamSelected(exam)) },
                        onSeeAll = { onNavigation(HomeNavigation.NavigateToExams) }
                    )
                    notesAdapter(notes = notesState.notes, totalCount = notesState.totalCount)
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