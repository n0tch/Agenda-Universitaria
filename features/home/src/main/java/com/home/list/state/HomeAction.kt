package com.home.list.state

import com.core.designsystem.components.fab.FabItemEnum
import com.example.model.Note
import com.example.model.Subject
import com.example.model.event.Event
import java.time.DayOfWeek

sealed class HomeAction {
    data class DaySelected(val dayOfWeek: DayOfWeek) : HomeAction()
    data class EventSelected(val event: Event) : HomeAction()
    data class NoteClicked(val note: Note) : HomeAction()
    data class TimeTableConfig(val subject: Subject) : HomeAction()
    data class FabClicked(val fabItem: FabItemEnum) : HomeAction()
}
