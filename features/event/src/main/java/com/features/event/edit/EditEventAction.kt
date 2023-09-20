package com.features.event.edit

import com.core.common.viewmodel.Action
import com.example.model.event.EventCompound

internal sealed class EditEventAction : Action {
    data class SaveEvent(
        val eventCompound: EventCompound,
        val subjectId: Int
    ) : EditEventAction()

    object OnBack: EditEventAction()
}
