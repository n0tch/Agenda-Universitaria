package com.core.domain

import com.core.common.Result
import com.core.data.repository.event.EventRepository
import com.example.model.event.EventCompound
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {

    fun saveEvent(event: EventCompound, subjectId: Int) = flow<Result<EventCompound>> {
        val savedEvent = eventRepository.saveEvent(event, subjectId)
        event.eventNotification?.let { eventRepository.saveNotification(it) }
        event.eventScore?.let { eventRepository.saveScore(it) }
        emit(Result.Success(savedEvent))
    }

    fun fetchEvents() = flow<Result<List<EventCompound>>>{
        val events = eventRepository.fetchEvents()
        emit(Result.Success(events))
    }

    suspend fun fetchEventBySubjectId(subjectId: Int): Result<List<EventCompound>> = try {
        val event = eventRepository.fetchEventsBySubjectId(subjectId)
        Result.Success(event)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
