package com.example.agendauniversitaria.feature.notes

import android.os.Bundle
import androidx.navigation.NavType
import com.example.agendauniversitaria.domain.model.Note
import com.google.gson.Gson

class NoteType: NavType<Note>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Note? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Note {
        return Gson().fromJson(value, Note::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Note) {
        bundle.putParcelable(key, value)
    }
}