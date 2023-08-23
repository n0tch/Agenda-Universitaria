package com.core.database.realmModel

import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId

class TimetableRealm: RealmObject {
    var id: ObjectId = ObjectId()
    var weekDays: String = ""
    var startTime: Long = 0L
    var endTime: Long = 0L
    var subjectId: String = ""
}
