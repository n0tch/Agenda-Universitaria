package com.example.model

import java.io.Serializable

data class Subject(
    val id: String = "",
    val name: String = "",
    val place: String = "",
    val teacher: String = ""
) : Serializable
