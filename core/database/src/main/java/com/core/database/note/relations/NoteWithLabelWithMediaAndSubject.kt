package com.core.database.note.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.core.database.label.LabelEntity
import com.core.database.media.MediaEntity
import com.core.database.note.NoteEntity
import com.core.database.subject.SubjectEntity

data class NoteWithLabelWithMediaAndSubject(
    @Embedded val note: NoteEntity,

    @Relation(
        parentColumn = "noteId",
        entityColumn = "labelId",
        associateBy = Junction(NoteLabelCrossRef::class)
    )
    val labels: List<LabelEntity>,

    @Relation(
        parentColumn = "noteId",
        entityColumn = "mediaId",
        associateBy = Junction(NoteMediaCrossRef::class)
    )
    val medias: List<MediaEntity> = emptyList(),

    @Relation(
        parentColumn = "noteSubjectId",
        entityColumn = "subjectId"
    )
    val subject: SubjectEntity
)

data class NoteWithLabel(
    @Embedded val note: NoteEntity,

    @Relation(
        parentColumn = "noteId",
        entityColumn = "labelId",
        associateBy = Junction(NoteLabelCrossRef::class)
    )
    val labels: List<LabelEntity>,
)