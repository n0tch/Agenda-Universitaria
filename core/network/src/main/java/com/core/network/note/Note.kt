package com.core.network.note

import com.core.network.model.noteResponse.NoteResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
        fetchNotes(userId)
        emit(noteId)
    }

    override fun fetchNotes(userId: String): Flow<List<NoteResponse?>> = callbackFlow {
        firebaseDatabase
            .reference
            .child(userId)
            .child(NOTE_PATH)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.map { it.getValue(NoteResponse::class.java) }
                    this@callbackFlow.trySend(items)
                    close()
                }

                override fun onCancelled(error: DatabaseError) {
                    close()
                    throw error.toException()
                }
            })

        awaitClose()
    }

    companion object {
        private const val NOTE_PATH = "NOTE"
    }
}
