package com.home.list.state

import com.example.model.NotesWithCountCompound

data class HomeListState(
    val noteState: NotesWithCountCompound = NotesWithCountCompound(),
    val subjectConfigState: SubjectConfigState = SubjectConfigState(),
    val eventsState: EventState = EventState(),
    val timetableState: TimetableState = TimetableState(),
    val currentUserState: CurrentUserState = CurrentUserState()
)
