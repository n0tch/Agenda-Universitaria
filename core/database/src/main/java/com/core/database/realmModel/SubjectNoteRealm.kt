package com.core.database.realmModel

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


open class SubjectNoteRealm: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var title: String = ""
    var body: String = ""
    var label: String = ""
    var subjectRealm: SubjectRealm? = null
}
