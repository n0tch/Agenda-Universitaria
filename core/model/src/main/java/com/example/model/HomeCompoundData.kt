package com.example.model

data class HomeCompoundData(
    val timetableEntries: List<TimetableEntry>,
    val subjects: List<Subject>
) {
    companion object {

    }
}
