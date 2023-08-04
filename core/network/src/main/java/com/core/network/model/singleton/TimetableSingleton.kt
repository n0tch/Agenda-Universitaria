package com.core.network.model.singleton

import com.core.network.model.timetableResponse.TimetableResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableSingleton @Inject constructor() {
    var timetableList: List<TimetableResponse>? = null
}
