package com.core.domain

import android.util.Log
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.core.data.repository.user.UserRepository
import com.example.model.Timetable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository,
    private val userRepository: UserRepository
) {

    fun fetchTimetableByDay(
        weekDayName: DayOfWeek
    ): Flow<Result<List<Timetable>>> = flow {
        timetableRepository
            .fetchTimetableByDay(weekDayName)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect {
                Log.e("HomeUseCase", it.data.toString())
                emit(it)
            }
    }

    suspend fun fetchWeeklyTimeTable(): Flow<Result<Map<DayOfWeek, List<Timetable>>>> = flow {
        timetableRepository
            .fetchWeeklyTimetable()
            .catch {
                Log.e("fetchWeeklyTimeTable error", it.message.toString())
                emit(Result.Error(it as Exception))
            }
            .map { Result.Success(it) }
            .collect {
                Log.e("fetchWeeklyTimeTable", it.data.toString())
                emit(it)
            }
    }
}