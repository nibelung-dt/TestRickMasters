package com.tarasov.testrickmasters.data.database

//import io.realm.gradle.Realm
import androidx.annotation.DrawableRes
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId



class Database {

}

open class Camera(
    @PrimaryKey
    var id: String = ObjectId().toHexString(),
   // @Required // 4.
    var idBase: Int = 0,
    var name: String ="",
    @Required
    var room: String = "",
    var favorites: Boolean = false,
    var rec: Boolean = false,
    var snapshot: String = "",
): RealmObject()

/*
@DrawableRes
    var image: Int? = null // 5.
 */





