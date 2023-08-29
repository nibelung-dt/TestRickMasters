package com.tarasov.testrickmasters.data.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class CameraRealm: RealmObject() {
    @PrimaryKey
    var id: String = ObjectId().toHexString()
    // @Required // 4.
    var name: String = ""
    var baseId: Int = 0
    @Required
    var room: String = ""
    var favorites: Boolean = false
    var rec: Boolean = false
    var snapshot: String = ""
}




