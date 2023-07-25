package com.example.agendauniversitaria.network.note

import com.example.agendauniversitaria.domain.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteDataSourceImp @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
) : NoteDataSource {
    override suspend fun saveNote(note: Note) {
        firebaseDatabase
            .getReference("notes")
            .child(firebaseAuth.currentUser?.uid ?: "")
            .setValue(note)
            .await()
    }
}