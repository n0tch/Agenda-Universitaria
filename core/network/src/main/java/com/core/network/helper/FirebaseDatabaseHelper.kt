package com.core.network.helper

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDatabaseHelper @Inject constructor (
    val firebaseDatabase: FirebaseDatabase
){

    suspend fun generateId(path: String): String {
        val dataRef = firebaseDatabase
            .reference
            .child(path)
            .push()

        return dataRef.get().await().key ?: ""
    }

    suspend fun <T>setData(path: String, data: T): String {
        val dataRef = firebaseDatabase
            .reference
            .child(path)
            .push()

        val dataId = dataRef.get().await().key ?: ""
        dataRef.setValue(data).await()
        return dataId
    }

    suspend inline fun <reified T>getData(path: String): List<T> {
        return firebaseDatabase
            .reference
            .child(path)
            .get()
            .await()
            .children
            .mapNotNull { it.getValue(T::class.java) }
    }
}