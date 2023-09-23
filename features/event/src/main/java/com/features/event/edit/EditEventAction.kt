package com.features.event.edit

import com.core.common.viewmodel.Action
import com.example.model.event.EventToSave

internal sealed class EditEventAction : Action {
    data class SaveEvent(
        val eventToSave: EventToSave
    ) : EditEventAction()

    object OnBack: EditEventAction()
}
