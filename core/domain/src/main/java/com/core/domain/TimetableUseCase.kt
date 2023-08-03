package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.core.data.repository.user.UserRepository
import com.example.model.TimetableEntry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimetableUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val timetableRepository: TimetableRepository,
    private val userRepository: UserRepository
) {

    fun saveTimetableEntry(entry: TimetableEntry) = flow<String> {
        val userId = userRepository.fetchCurrentUser().id

        timetableRepository.saveTimetableEntry(userId, entry)
            .flowOn(ioDispatcher)
            .collect {
                Log.e(TimetableUseCase::class.simpleName, it)
            }
    }

    fun fetchTimeTable(): Flow<Result<List<TimetableEntry>>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        timetableRepository
            .fetchTimetable(userId)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect {
                emit(it)
            }
    }
}