package com.core.network.timetable

import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.singleton.TimetableSingleton
import com.core.network.model.timetableResponse.TimetableResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TimetableDataProviderImp @Inject constructor(
    private val timetableSingleton: TimetableSingleton,
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper
): TimetableDataProvider {

    override fun saveTimetableEntry(userId: String, timetableResponse: TimetableResponse): Flow<String>  = flow {
        val timetableEntry = firebaseDatabaseHelper.setData( "$userId/$TIMETABLE_PATH/", timetableResponse)
        emit(timetableEntry.id ?: "")
    }

    override fun fetchTimetables(userId: String): Flow<List<TimetableResponse>> = flow {
        timetableSingleton.timetableList?.let {
            emit(it)
        } ?: run {
            val items = firebaseDatabaseHelper.getDataList<TimetableResponse>("$userId/$TIMETABLE_PATH")
            timetableSingleton.timetableList = items
            emit(items)
        }
    }

    override fun fetchTimetableByDay(userId: String, dayWeekName: String): Flow<String> = flow {
        emit("nice")
    }

    companion object{
        private const val TIMETABLE_PATH = "TIMETABLE"
    }
}
