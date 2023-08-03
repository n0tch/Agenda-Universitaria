package com.features.note.newnote

import android.os.Bundle
import androidx.navigation.NavType
import com.example.model.Note

class NoteType: NavType<Note>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Note? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Note {
        return Note()
    }

    override fun put(bundle: Bundle, key: String, value: Note) {
        bundle.putParcelable(key, value)
    }
}