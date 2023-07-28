package com.feature.navigation

import com.feature.navigation.note.NoteScreens

enum class HomeMainScreens(val label: String, val route: String) {
    HOME_NOTE("Notas", NoteScreens.NOTE.route)
}