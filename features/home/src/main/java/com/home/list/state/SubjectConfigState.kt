package com.home.list.state

import com.example.model.Subject

data class SubjectConfigState(
    var showDialog: Boolean = false,
    val subject: Subject = Subject()
)