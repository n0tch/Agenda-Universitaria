package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.core.data.repository.user.UserRepository
import com.example.model.Timetable
import com.example.model.TimetableCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val timetableRepository: TimetableRepository
) {

    fun fetchTimetableByDay(
        weekDayName: DayOfWeek
    ): Flow<Result<List<TimetableCompound>>> = flow<Result<List<TimetableCompound>>> {
        val timetable = timetableRepository.fetchTimetableByDay(weekDayName)
        emit(Result.Success(timetable))
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }

    suspend fun fetchWeeklyTimeTable(): Flow<Result<Map<DayOfWeek, List<TimetableCompound>>>> = flow<Result<Map<DayOfWeek, List<TimetableCompound>>>> {
        val timetables = timetableRepository.fetchWeeklyTimetable()
        emit(Result.Success(timetables))
    }.flowOn(ioDispatcher).catch {
        Log.e("fetchWeeklyTimeTable error", it.message.toString())
        emit(Result.Error(it as Exception))
    }
}