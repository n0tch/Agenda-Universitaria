package com.home.list.navigation

import android.content.Context
import com.core.common.viewmodel.Navigation
import com.home.list.FabItemHome

sealed class HomeNavigation: Navigation {
    object NavigateToEvents: HomeNavigation()
    object NavigateToNotes: HomeNavigation()

    class NavigateToEventById(val event: Int): HomeNavigation()

    class NavigateToNoteById(val noteId: Int): HomeNavigation()

    class NavigateToSubjectById(val subjectId: Int): HomeNavigation()

    class NavigateToScreenByName(val screenName: String): HomeNavigation()

    class NavigateToScreenByEnum(val item: FabItemHome): HomeNavigation()

    class NavigateToCalendar(val context: Context): HomeNavigation()
}
