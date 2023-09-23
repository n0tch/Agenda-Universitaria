package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.example.model.TimetableCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.DayOfWeek
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val timetableRepository: TimetableRepository
) {

    suspend fun fetchTimetableByDay(
        weekDayName: DayOfWeek
    ): Result<List<TimetableCompound>> = try {
        val timetable = timetableRepository.fetchTimetableByDay(weekDayName)
        Result.Success(timetable)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchWeeklyTimeTable(): Flow<Result<Map<DayOfWeek, List<TimetableCompound>>>> =
        flow<Result<Map<DayOfWeek, List<TimetableCompound>>>> {
            val timetables = timetableRepository.fetchWeeklyTimetable()
            emit(Result.Success(timetables))
        }.flowOn(ioDispatcher).catch {
            Log.e("fetchWeeklyTimeTable error", it.message.toString())
            emit(Result.Error(it as Exception))
        }
}
