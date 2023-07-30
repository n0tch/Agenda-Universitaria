package com.feature.navigation

import com.feature.navigation.note.noteGraphRoute
import com.feature.navigation.subject.subjectGraphRoute

enum class HomeMainScreens(val label: String, val route: String) {
    SUBJECT("Disciplinas", subjectGraphRoute),
    NOTE("Notas", noteGraphRoute),
    EXAMS("Provas", ""),
    HOMEWORK("Trabalhos", ""),
    NOTIFICATIONS("Notificações", "")
}
