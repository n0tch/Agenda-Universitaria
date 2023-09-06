package com.example.model

import java.io.Serializable

data class Subject(
    val id: Int = 0,
    val name: String = "",
    val place: String = "",
    val teacher: String = ""
) : Serializable {
    companion object {
        fun getMock() = Subject(
            id = 123,
            name = "Direito",
            place = "201",
            teacher = "Kleber"
        )
    }
}
