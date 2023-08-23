package com.core.database.timetable

import android.util.Log
import com.core.database.databaseModel.TimetableDatabaseModel
import com.core.database.realmModel.TimetableRealm
import com.core.database.realmModel.WeekDayEnum
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TimetableDaoImp @Inject constructor(
    private val realm: Realm
) : TimetableDao {
    override fun saveTimetable(): Flow<Boolean> = flow {
        val timetable = TimetableRealm().apply {
            weekDays = WeekDayEnum.TUESDAY.name
            startTime = 12
            endTime = 15
            subjectId = "123"
        }
        realm.write { copyToRealm(timetable) }.asFlow().collect {
            Log.e("asaaa", "eeddede")
        }
    }

    override fun fetchTimetable(): Flow<Map<String, List<TimetableDatabaseModel>>> = flow {
        val timetables = realm.query<TimetableRealm>().find().map {
            TimetableDatabaseModel(
                id = it.id.toString(),
                weekDays = it.weekDays,
                startTime = it.startTime,
                endTime = it.endTime,
                subjectId = it.subjectId,
            )
        }.groupBy {
            it.weekDays
        }

        Log.e("tametables", timetables.toString())
        emit(timetables)
    }
}