package com.core.network.helper

import com.core.network.model.BaseModel
import com.core.network.subject.SubjectDataProviderImp
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDatabaseHelper @Inject constructor (
    val firebaseDatabase: FirebaseDatabase
){

    suspend fun <T: BaseModel>setData(path: String, data: T): T {
        val dataRef = firebaseDatabase
            .reference
            .child(path)
            .push()

        val dataId = dataRef.get().await().key ?: ""
        dataRef.setValue(data.apply { id = dataId }).await()
        return data
    }

    suspend inline fun <reified T>getDataList(path: String): List<T> {
        return firebaseDatabase
            .reference
            .child(path)
            .get()
            .await()
            .children
            .mapNotNull { it.getValue(T::class.java) }
    }

    suspend inline fun <reified T>getData(path: String): T {
        return firebaseDatabase
            .getReference(path)
            .get()
            .await()
            .getValue(T::class.java) ?: throw Exception("no item fount")
    }

    suspend fun deleteData(path: String){
        firebaseDatabase
            .getReference(path)
            .removeValue()
            .await()
    }
}
