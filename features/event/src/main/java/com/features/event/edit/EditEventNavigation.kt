package com.features.event.edit

import com.core.common.viewmodel.Navigation

sealed class EditEventNavigation: Navigation{
    object OnBack: EditEventNavigation()
}
