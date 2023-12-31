package com.core.domain

import com.core.common.Result
import com.core.data.repository.event.EventRepository
import com.core.data.repository.schedulenotification.ScheduleNotificationRepository
import com.example.model.event.Event
import com.example.model.event.EventCompound
import com.example.model.event.EventNotificationDecorator
import com.example.model.event.EventToSave
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import java.util.Calendar
import javax.inject.Inject

class EventUseCase @Inject constructor(
    private val eventRepository: EventRepository,
    private val scheduleNotificationRepository: ScheduleNotificationRepository
) {

    suspend fun saveEvent(event: EventToSave): Result<Event> = try {
        val savedEvent = eventRepository.saveEvent(
            event = event.event,
            subjectId = event.subjectId,
            labelId = event.label.id
        )

        if(event.hasScore){
            eventRepository.saveScore(event.eventScore, savedEvent.id)
        }

        if(event.hasNotification){
            eventRepository.saveNotification(event.eventNotification, savedEvent.id)
            scheduleNotificationRepository.scheduleAtExactTime(
                calendars = listOf(Calendar.getInstance().apply { timeInMillis = event.event.date}),
                EventNotificationDecorator(savedEvent, "Prova"),
                NotificationEarlier.IN_TIME,
                NotificationPeriod.ONCE
            )
        }

//        event.date.let { date ->
//            val calendar = Calendar.getInstance().apply {
//                timeInMillis = date
//                set(Calendar.HOUR_OF_DAY, event.hour)
//                set(Calendar.MINUTE, event.minute)
//            }
//            eventRepository.saveNotification(
//                EventNotification(
//                    notifyAt = calendar.timeInMillis,
//                    eventId = savedEvent.id
//                )
//            )
//            scheduleNotificationRepository.scheduleAtExactTime(
//                calendars = listOf(calendar),
//                EventNotificationDecorator(savedEvent, "Prova"),
//                NotificationEarlier.IN_TIME,
//                NotificationPeriod.ONCE
//            )
//        }
        Result.Success(savedEvent)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchEvents(): Result<List<EventCompound>> = try {
        val events = eventRepository.fetchEvents()
        Result.Success(events)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchEvents(limit: Int): Result<List<EventCompound>> = try {
        val events = eventRepository.fetchEvents(limit)
        Result.Success(events)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchEventBySubjectId(subjectId: Int): Result<List<EventCompound>> = try {
        val event = eventRepository.fetchEventsBySubjectId(subjectId)
        Result.Success(event)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchEventsGroupedByDate(): Map<Calendar, List<Event>>{
        return eventRepository.fetchEventsGroupedByDate()
    }
}
