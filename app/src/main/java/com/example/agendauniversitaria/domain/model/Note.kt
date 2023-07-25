package com.example.agendauniversitaria.domain.model

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val title: String = "",
    val body: String = ""
): Parcelable{
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}