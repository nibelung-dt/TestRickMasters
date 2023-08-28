package com.tarasov.testrickmasters.data.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class DoorRealm: RealmObject() {
    @PrimaryKey
    var id: String = ObjectId().toHexString()
    var name: String = ""
    var baseId: Int = 0
    var snapshot: String = ""
    var room: String = ""
    var favorites: Boolean = false
}