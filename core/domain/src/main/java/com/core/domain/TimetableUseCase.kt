package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.example.model.Timetable
import com.example.model.TimetableCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.DayOfWeek
import javax.inject.Inject

class TimetableUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val timetableRepository: TimetableRepository
) {

    fun saveTimetableEntry(entry: Timetable) = flow<Result<Timetable>> {
        val timetable = timetableRepository.saveTimetableEntry(entry)
        emit(Result.Success(timetable))
    }.flowOn(ioDispatcher).catch {
        Log.e("erro", it.toString())
        emit(Result.Error(it as Exception))
    }

    fun fetchTimetableByWeekDay(weekDay: DayOfWeek): Flow<Result<List<TimetableCompound>>> =
        flow<Result<List<TimetableCompound>>> {
            val timetables = timetableRepository.fetchTimetableByDay(weekDay)
            emit(Result.Success(timetables))
        }.flowOn(ioDispatcher).catch {
            Log.e("erro", it.toString())
            emit(Result.Error(it as Exception))
        }

    fun scheduleTimeTableNotification(millisToSchedule: List<Long>) {
        Log.e("scheduleTimeTableNotification", millisToSchedule.toString())
    }
}
