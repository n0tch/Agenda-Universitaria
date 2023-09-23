package com.core.database.label.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.core.database.label.LabelEntity
import com.core.database.note.NoteEntity
import com.core.database.note.relations.NoteLabelCrossRef

data class LabelWithNotes(
    @Embedded val label: LabelEntity,

    @Relation(
        parentColumn = "labelId",
        entityColumn = "noteId",
        associateBy = Junction(NoteLabelCrossRef::class)
    )
    val notes: List<NoteEntity>
)
