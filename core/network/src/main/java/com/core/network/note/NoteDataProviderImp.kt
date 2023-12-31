package com.core.network.note

import android.util.Log
import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.noteResponse.NoteResponse
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteDataProviderImp @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper
) : NoteDataProvider {

    override fun saveNote(userId: String, note: NoteResponse): Flow<NoteResponse> = flow {
        val savedNote = firebaseDatabaseHelper.setData("$userId/$NOTE_PATH/", note)
        emit(savedNote)
    }

    override fun fetchNotes(userId: String): Flow<List<NoteResponse?>> = flow {
        val items = firebaseDatabase
            .reference
            .child("$userId/$NOTE_PATH")
            .get()
            .await()
            .children
            .mapNotNull { it.getValue(NoteResponse::class.java) }

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

    override fun fetchNotesBySubject(
        userId: String,
        subject: String
    ): Flow<List<NoteResponse?>> = callbackFlow {
        val ref = firebaseDatabase
            .getReference("$userId/$NOTE_PATH/")
            .orderByChild("subject")
            .equalTo(subject)
            .get()
            .await()

        val items = ref.children.map { it.getValue(NoteResponse::class.java) }
        trySend(items)
        close()
        awaitClose()
    }

    override fun fetchNoteById(
        userId: String,
        noteId: String
    ): Flow<NoteResponse?> = flow {
        val item = firebaseDatabase
            .getReference("$userId/$NOTE_PATH/$noteId")
            .get()
            .await()

        val note = item.getValue(NoteResponse::class.java)
        emit(note)
    }

    companion object {
        private const val NOTE_PATH = "NOTES"
        private const val DEFAULT_NOTES_LABEL = "DEFAULT_NOTES_LABEL"
    }
}
