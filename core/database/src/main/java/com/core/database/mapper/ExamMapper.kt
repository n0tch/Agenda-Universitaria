package com.core.database.mapper

import com.core.database.databaseModel.ExamDataModel
import com.core.database.realmModel.ExamRealm
import io.realm.kotlin.types.RealmInstant
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class ExamMapper {
    fun mapFromRealm(exam: ExamRealm?) = ExamDataModel(
        id = exam?.id.toString(),
        name = exam?.name ?: "",
        score = exam?.score ?: 0F,
        color = exam?.color ?: 1,
        subjectId = exam?.subjectId ?: "",
        //TODO: fix this
        subjectNotes = emptyList(),
        date = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(
                exam?.date?.epochSeconds ?: 0L,
                exam?.date?.nanosecondsOfSecond?.toLong() ?: 0L
            ), ZoneId.systemDefault()
        )
    )

    fun mapToRealm(examDataModel: ExamDataModel) = ExamRealm().apply {
        name = examDataModel.name
        score = examDataModel.score
        color = examDataModel.color
        subjectId = examDataModel.subjectId
        //TODO: Fix this
//    var subjectNotes: RealmList<SubjectNoteRealm> = realmListOf()
        date = RealmInstant.from(
            examDataModel.date.atZone(ZoneId.systemDefault()).toInstant().epochSecond,
            examDataModel.date.atZone(ZoneId.systemDefault()).toInstant().nano
        )
    }
}