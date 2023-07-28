package com.core.network.note

import android.util.Log
import com.core.network.model.noteResponse.NoteLabelResponse
import com.core.network.model.noteResponse.NoteResponse
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteDataProviderImp @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : NoteDataProvider {
    override fun saveNote(userId: String, note: NoteResponse): Flow<String> = flow {
        val dataRef = firebaseDatabase
            .reference
            .child("$userId/$NOTE_PATH/")
            .push()

        val noteId = dataRef.get().await().key ?: ""
        dataRef.setValue(note).await()
        emit(noteId)
    }

    override fun fetchNotes(userId: String): Flow<List<NoteResponse?>> = flow {
        val ref = firebaseDatabase
            .reference
            .child(userId)
            .child(NOTE_PATH)
            .get()
            .await()

        val items = ref.children.map { it.getValue(NoteResponse::class.java) }
        emit(items)
    }

    override fun saveNoteLabel(noteLabel: String): Flow<String> = flow {
        val ref = firebaseDatabase.reference.child("$DEFAULT_NOTES_LABEL/")

        ref.setValue(listOf("PROVA", "TRABALHO", "RESUMO")).await()
        Log.e("asas", "asas")
        emit("")
    }

    override fun getNoteLabels(): Flow<List<String?>> = flow {
        val ref = firebaseDatabase
            .reference
            .child(DEFAULT_NOTES_LABEL)
            .get()
            .await()

        val items = ref.children.map { it.getValue(String::class.java) }
        emit(items)
    }

    companion object {
        private const val NOTE_PATH = "NOTES"
        private const val DEFAULT_NOTES_LABEL = "DEFAULT_NOTES_LABEL"
    }
}
