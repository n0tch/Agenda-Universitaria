package com.core.database.event.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.core.database.event.EventEntity
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.score.ScoreEntity
import com.core.database.label.LabelEntity
import com.core.database.subject.SubjectEntity

data class EventAndNotificationAndScoreAndSubject(
    @Embedded val event: EventEntity,

    @Relation(
        parentColumn = "eventId",
        entityColumn = "eventId",
    )
    val notification: NotificationEntity? = null,

    @Relation(
        parentColumn = "eventId",
        entityColumn = "eventId"
    )
    val score: ScoreEntity? = null,

    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subjectId"
    )
    val subject: SubjectEntity,

    @Relation(
        parentColumn = "labelId",
        entityColumn = "labelId"
    )
    val label: LabelEntity
)
