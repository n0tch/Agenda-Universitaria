package com.feature.navigation

import androidx.annotation.DrawableRes
import com.features.navigation.R

enum class HomeMainScreens(val label: String) {
    SUBJECT("Disciplinas"),
    NOTE("Notas"),
    EXAMS("Provas"),
    HOMEWORK("Trabalhos"),
    NOTIFICATIONS("Notificações"),
    TIMETABLE("Grade horária")
}

enum class HomeMainScreenAddRoutes(val label: String, val route: String){
    ADD_NOTE("Add note", ""),
    ADD_SUBJECT("Add disciplina", ""),
    ADD_TIMETABLE("Add Timetable", ""),
    ADD_EXAM("Add exam", "")
}

enum class BottomBarScreens(val label: String, @DrawableRes val icon: Int){
    HOME("home", R.drawable.baseline_home_24),
    NOTE("Notas", R.drawable.baseline_sticky_note_2_24),
    CALENDAR("Disciplinas", R.drawable.baseline_calendar_month_24),
    PROFILE("Perfil", R.drawable.baseline_person_24),
}
