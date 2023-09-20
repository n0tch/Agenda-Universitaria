package com.core.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.database.Converter
import com.core.database.event.EventDao
import com.core.database.event.EventEntity
import com.core.database.event.notification.NotificationDao
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.score.ScoreDao
import com.core.database.event.score.ScoreEntity
import com.core.database.exam.ExamDao
import com.core.database.exam.ExamEntity
import com.core.database.exam.relations.label.ExamLabelCrossRef
import com.core.database.note.NoteDao
import com.core.database.note.NoteEntity
import com.core.database.label.LabelDao
import com.core.database.label.LabelEntity
import com.core.database.media.NoteMediaDao
import com.core.database.media.MediaEntity
import com.core.database.note.relations.NoteLabelCrossRef
import com.core.database.note.relations.NoteLabelDao
import com.core.database.note.relations.NoteMediaCrossRef
import com.core.database.subject.SubjectDao
import com.core.database.subject.SubjectEntity
import com.core.database.timetable.TimetableDao
import com.core.database.timetable.TimetableEntity
import com.core.database.timetable.relations.TimetableSubjectCrossRef

@Database(
    entities = [
        NoteEntity::class,
        LabelEntity::class,
        SubjectEntity::class,
        TimetableEntity::class,
        ExamEntity::class,
        MediaEntity::class,
        NoteLabelCrossRef::class,
        NoteMediaCrossRef::class,
        ExamLabelCrossRef::class,
        TimetableSubjectCrossRef::class,
        EventEntity::class,
        NotificationEntity::class,
        ScoreEntity::class
    ], version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun labelDao(): LabelDao
    abstract fun subjectDao(): SubjectDao
    abstract fun timetableDao(): TimetableDao
    abstract fun examDao(): ExamDao
    abstract fun noteMediaDao(): NoteMediaDao
    abstract fun noteLabelDao(): NoteLabelDao
    abstract fun eventDao(): EventDao
    abstract fun notificationDao(): NotificationDao
    abstract fun scoreDao(): ScoreDao
}
