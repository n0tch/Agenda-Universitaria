package com.home.list.navigation

import com.core.common.viewmodel.Navigation

sealed class HomeNavigation: Navigation {
    object NavigateToEvents: HomeNavigation()
    object NavigateToNotes: HomeNavigation()

    class NavigateToEventById(val event: Int): HomeNavigation()

    class NavigateToNoteById(val noteId: Int): HomeNavigation()

    class NavigateToSubjectById(val subjectId: Int): HomeNavigation()

    class NavigateToScreenByName(val screenName: String): HomeNavigation()
}
