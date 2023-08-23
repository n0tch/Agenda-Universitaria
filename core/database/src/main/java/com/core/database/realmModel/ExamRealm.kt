package com.core.database.realmModel

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ExamRealm: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var score: Float = 0f
    var color: Int = 1
    var subjectId: String = ""
    //TODO: Fix this
//    var subjectNotes: RealmList<SubjectNoteRealm> = realmListOf()
    var date: RealmInstant? = null
}
