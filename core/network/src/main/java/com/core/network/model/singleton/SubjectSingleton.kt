package com.core.network.model.singleton

import com.core.network.model.subjectResponse.SubjectModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubjectSingleton @Inject constructor() {
    var subjectList: MutableList<SubjectModel>? = null
}