package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.schedulenotification.ScheduleNotificationRepository
import com.core.data.repository.subject.SubjectRepository
import com.core.data.repository.timetable.TimetableRepository
import com.example.model.Subject
import com.example.model.SubjectCompound
import com.example.model.SubjectNotificationDecorator
import com.example.model.Timetable
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.Calendar
import javax.inject.Inject

class SubjectUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val subjectRepository: SubjectRepository,
    private val timetableRepository: TimetableRepository,
    private val scheduleNotificationRepository: ScheduleNotificationRepository
) {

    fun saveSubject(subject: Subject) = flow<Result<Subject>> {
        val savedSubject = subjectRepository.saveSubject(subject)
        emit(Result.Success(savedSubject))
    }.flowOn(ioDispatcher).catch {
        Log.e("saveSubject", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    suspend fun fetchSubjectsWithMvi(): Result<List<Subject>> = try {
        val subjects = subjectRepository.fetchSubjects()
        Result.Success(subjects)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchSubjects(): Result<List<Subject>> = try {
        val subjects = subjectRepository.fetchSubjects()
        Result.Success(subjects)
    }catch(exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchSubject(subjectId: Int): Result<SubjectCompound> = try {
        val subject = subjectRepository.fetchSubjectById(subjectId)
        Result.Success(subject)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun deleteSubjectName(subject: Subject): Result<Boolean> = try {
        val deleted = subjectRepository.deleteSubject(subject)
        Result.Success(deleted)
    }catch (exception: Exception){
        Result.Error(exception)
    }

    suspend fun searchSubjectByName(query: String): Result<List<Subject>> = try {
        val subjects = subjectRepository.searchSubjectsByName(query)
        Result.Success(subjects)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun saveSubject(
        subject: Subject,
        timetable: List<Timetable>,
        notificationEnabled: Boolean,
        notificationEarlier: NotificationEarlier
    ) : Result<Boolean> = try {
        val savedSubject = subjectRepository.saveSubject(subject)
        timetableRepository.saveTimetableEntry(timetable, savedSubject.id)

        if (notificationEnabled) {
            scheduleNotificationRepository.scheduleAtExactTime(
                timetable.map { Calendar.getInstance().apply { timeInMillis = it.startTime } },
                SubjectNotificationDecorator(subject),
                notificationEarlier,
                NotificationPeriod.WEEKLY
            )
        }

        Result.Success(true)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
