package com.home.list

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.fab.FabItemEnum
import com.core.designsystem.components.fab.FabMenu
import com.home.list.adapters.eventAdapter
import com.home.list.adapters.notesAdapter
import com.home.list.adapters.timetableAdapter
import com.home.list.component.HomeProfileHeader
import com.home.list.component.WeeklyDaySelector
import com.home.list.drawer.DrawerBody
import com.home.list.navigation.HomeNavigation
import com.home.list.state.HomeAction
import com.home.list.state.HomeListState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    screenList: List<String>,
    homeListState: HomeListState,
    onAction: (HomeAction) -> Unit = {},
    onNavigation: (HomeNavigation) -> Unit = {}
) {
    val context = LocalContext.current
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
                onDrawerItemClicked = {
                    if(it == "Calendario")
                        onNavigation(HomeNavigation.NavigateToCalendar(context))
                    else
                        onNavigation(HomeNavigation.NavigateToScreenByName(it))
                }
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
                            selectedDay = homeListState.timetableState.selectedDayOfWeek,
                            onClick = {
                                onAction(HomeAction.DaySelected(it))
                            }
                        )
                    }
                },
                floatingActionButton = {
                    FabMenu(
                        items = listOf(
                            FabItemHome.EXAM,
                            FabItemHome.NOTE,
                            FabItemHome.DISCIPLINE,
                            FabItemHome.TIMETABLE
                        ),
                        onFabClicked = {
                            onAction(HomeAction.FabClicked(it))
                        }
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
                        selectedDay = homeListState.timetableState.selectedDayOfWeek,
                        timetable = homeListState.timetableState.timetables,
                        onTimetableClicked = { subject -> onNavigation(HomeNavigation.NavigateToSubjectById(subject.id)) },
                        onConfigClicked = { subject -> onAction(HomeAction.TimeTableConfig(subject)) }
                    )
                    eventAdapter(
                        events = homeListState.eventsState.events,
                        onEventClicked = { event -> onNavigation(HomeNavigation.NavigateToEventById(event.id)) },
                        onSeeAll = { onNavigation(HomeNavigation.NavigateToEvents) }
                    )
                    notesAdapter(
                        notes = homeListState.noteState.note,
                        onNoteClicked = { note -> onNavigation(HomeNavigation.NavigateToNoteById(note.id)) },
                        onSeeAll = { onNavigation(HomeNavigation.NavigateToNotes) }
                    )
                }
            }
        }
    )
}

enum class FabItemHome(
    override val icon: ImageVector,
    override val label: String
): FabItemEnum {
    EXAM(Icons.Filled.Hexagon, "Add Event"),
    NOTE(Icons.Filled.Note, "Add note"),
    TIMETABLE(Icons.Filled.Newspaper, "Add disciplina"),
    DISCIPLINE(Icons.Filled.CalendarMonth, "Add Timetable")
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