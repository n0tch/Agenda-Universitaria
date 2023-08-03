package com.core.domain

import com.core.common.Result
import com.core.data.repository.timetable.TimetableRepository
import com.core.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository,
    private val userRepository: UserRepository
){

    suspend fun fetchTimetableByDay(weekDayName: String): Flow<Result<String>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        timetableRepository
            .fetchTimetableByDay(userId, weekDayName)
            .map{ Result.Success(it) }
            .collect{ emit(it) }
    }

}