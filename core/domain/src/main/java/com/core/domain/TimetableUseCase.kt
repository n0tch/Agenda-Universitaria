package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.core.data.repository.user.UserRepository
import com.example.model.Timetable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import javax.inject.Inject

class TimetableUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val timetableRepository: TimetableRepository,
    private val userRepository: UserRepository
) {

    fun saveTimetableEntry(entry: Timetable) = flow<String> {
        val userId = userRepository.fetchCurrentUser().id

        timetableRepository.saveTimetableEntry(userId, entry)
            .flowOn(ioDispatcher)
            .collect {
                Log.e(TimetableUseCase::class.simpleName, it.toString())
            }
    }

    fun fetchTimeTable(): Flow<Result<Map<String, List<Timetable>>>> = flow {
//        val userId = userRepository.fetchCurrentUser().id
//        timetableRepository
//            .fetchTimetable(userId)
//            .flowOn(ioDispatcher)
//            .catch { emit(Result.Error(it as Exception)) }
//            .map { Result.Success(it) }
//            .collect {
//                emit(it)
//            }
    }

    fun fetchTimetableByWeekDay(weekDay: DayOfWeek): Flow<Result<List<Timetable>>> = flow {
        timetableRepository.fetchTimetableByDay(weekDay)
            .flowOn(ioDispatcher)
            .catch {
                Log.e("erro", it.toString())
                emit(Result.Error(it as Exception))
            }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }
}
