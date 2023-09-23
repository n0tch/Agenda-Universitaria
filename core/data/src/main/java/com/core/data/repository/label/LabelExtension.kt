package com.core.data.repository.label

import com.core.database.label.LabelEntity
import com.example.model.Label

internal fun Label.toEntity() = LabelEntity(
    name = name
)

internal fun LabelEntity.toLabel() = Label(
    id = labelId,
    name = name ?: "",
)
