package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.example.model.Timetable
import com.example.model.TimetableCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.DayOfWeek
import javax.inject.Inject

class TimetableUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val timetableRepository: TimetableRepository
) {

    fun saveTimetableEntries(entries: List<Timetable>, subjectId: Int) = flow<Result<Timetable>> {
        entries.forEach { it.subjectId = subjectId }
        timetableRepository.saveTimetableEntry(entries, 0)

        emit(Result.Success(Timetable()))
    }.flowOn(ioDispatcher).catch {
        Log.e("erro", it.toString())
        emit(Result.Error(it as Exception))
    }

    fun fetchTimetableByWeekDay(weekDay: DayOfWeek) = flow<Result<List<TimetableCompound>>> {
        val timetables = timetableRepository.fetchTimetableByDay(weekDay)
        emit(Result.Success(timetables))
    }.flowOn(ioDispatcher).catch {
        Log.e("erro", it.toString())
        emit(Result.Error(it as Exception))
    }

    suspend fun fetchTimetableBySubjectId(subjectId: Int): Result<Map<DayOfWeek, List<Timetable>>> = try{
        val timetables = timetableRepository.fetchTimetableBySubjectId(subjectId)
        Result.Success(timetables)
    }catch (exception: Exception){
        Result.Error(exception)
    }

    suspend fun scheduleTimeTableNotification(millisToSchedule: List<Long>) {
        Log.e("scheduleTimeTableNotification", millisToSchedule.toString())
//        appNotificationManager.scheduleNotification(4, 21, 25)
    }
}
