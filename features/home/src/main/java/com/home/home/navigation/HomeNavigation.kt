package com.home.home.navigation

import com.home.home.base.Navigation

sealed class HomeNavigation: Navigation {
    object NavigateToExams: HomeNavigation()
    object NavigateToNotes: HomeNavigation()

    class NavigateToExamById(val examId: Int): HomeNavigation()

    class NavigateToNoteById(val noteId: Int): HomeNavigation()

    class NavigateToScreenByName(val screenName: String): HomeNavigation()
}
