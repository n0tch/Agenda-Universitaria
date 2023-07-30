package com.feature.navigation.subject


enum class SubjectScreens(val route: String, val argument: String? = "") {
    SUBJECTS("subjects"),
    SUBJECT_DETAIL("subject_detail", "subject_name"),
    EXAM("exam")
}