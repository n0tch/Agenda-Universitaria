package com.features.event.edit

import com.core.common.viewmodel.Action
import com.example.model.event.EventSaveRequest

internal sealed class EditEventAction : Action {
    data class SaveEvent(
        val eventSaveRequest: EventSaveRequest
    ) : EditEventAction()

    object OnBack: EditEventAction()
}
