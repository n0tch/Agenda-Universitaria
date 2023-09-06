package com.core.database.subject.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.core.database.note.NoteEntity
import com.core.database.subject.SubjectEntity

data class SubjectWithNotes(
    @Embedded val subject: SubjectEntity,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "noteSubjectId"
    )
    val notes: List<NoteEntity>
)
