package com.example.model

data class HomeCompoundData(
    val timetableEntries: List<TimetableEntry>,
    val subjects: List<Subject>
) {
    companion object {
        fun generateMock() = HomeCompoundData(
            timetableEntries = listOf(
                TimetableEntry(
                    weekDays = listOf("segunda", "terca"),
                    startTime = "10:00",
                    endTime = "12:00",
                    subjectId = "Direito"
                ),
                TimetableEntry(
                    weekDays = listOf("segunda", "terca"),
                    startTime = "13:00",
                    endTime = "15:00",
                    subjectId = "Filosofia"
                )
            ),
            subjects = listOf(
                Subject.getMock(),
                Subject.getMock()
            )
        )
    }
}
