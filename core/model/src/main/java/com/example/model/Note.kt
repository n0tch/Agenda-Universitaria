package com.example.model

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val label: String = ""
): Parcelable{
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}