package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val label: String = "",
    val subject: String = ""
): Parcelable
