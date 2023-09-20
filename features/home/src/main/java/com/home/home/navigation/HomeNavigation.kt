package com.home.home.navigation

import com.core.common.viewmodel.Navigation

sealed class HomeNavigation: Navigation {
    object NavigateToExams: HomeNavigation()
    object NavigateToNotes: HomeNavigation()

    class NavigateToExamById(val examId: Int): HomeNavigation()

    class NavigateToNoteById(val noteId: Int): HomeNavigation()

    class NavigateToScreenByName(val screenName: String): HomeNavigation()
}
