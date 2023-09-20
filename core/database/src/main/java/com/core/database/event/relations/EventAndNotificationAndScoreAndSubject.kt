package com.core.database.event.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.core.database.event.EventEntity
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.score.ScoreEntity
import com.core.database.subject.SubjectEntity

data class EventAndNotificationAndScoreAndSubject(
    @Embedded val event: EventEntity,

    @Relation(
        parentColumn = "eventNotificationId",
        entityColumn = "notificationId",
    )
    val notification: NotificationEntity? = null,

    @Relation(
        parentColumn = "scoreId",
        entityColumn = "scoreId"
    )
    val score: ScoreEntity? = null,

    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subjectId"
    )
    val subject: SubjectEntity
)
