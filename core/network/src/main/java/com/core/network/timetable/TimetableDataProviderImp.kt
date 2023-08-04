package com.core.network.timetable

import android.util.Log
import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.singleton.TimetableSingleton
import com.core.network.model.timetableResponse.TimetableResponse
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TimetableDataProviderImp @Inject constructor(
    private val timetableSingleton: TimetableSingleton,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper
): TimetableDataProvider {

    override fun saveTimetableEntry(userId: String, timetableResponse: TimetableResponse): Flow<String>  = flow {
        val timetableEntryId = firebaseDatabaseHelper.setData( "$userId/$TIMETABLE_PATH/", timetableResponse)
        emit(timetableEntryId)
    }

    override fun fetchTimetables(userId: String): Flow<List<TimetableResponse>> = flow {
        timetableSingleton.timetableList?.let {
            emit(it)
        } ?: run {
            val items = firebaseDatabaseHelper.getData<TimetableResponse>("$userId/$TIMETABLE_PATH")
            timetableSingleton.timetableList = items
            emit(items)
        }
    }

    override fun fetchTimetableByDay(userId: String, dayWeekName: String): Flow<String> = flow {
        val items = firebaseDatabase.reference.child("$userId/$TIMETABLE_PATH/").get().await()
        Log.e("fetchTimetableByDay", items.toString())
        emit("nice")
    }

    companion object{
        private const val TIMETABLE_PATH = "TIMETABLE"
        private const val WEEK_DAY_PATH = "weekDays"
    }
}