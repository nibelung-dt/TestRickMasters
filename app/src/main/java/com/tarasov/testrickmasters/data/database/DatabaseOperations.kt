package com.tarasov.testrickmasters.data.database

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers

// https://www.section.io/engineering-education/using-realm-database-in-android/

class DatabaseOperations(
    private val config: RealmConfiguration
) {

    suspend fun insertTestObject(
        name: String
    ) {
        val realm = Realm.getInstance(config)
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val testObject = Camera(
                name = name
            )
            realmTransaction.insert(testObject)
        }
    }


    suspend fun insertCamera(
       idBase: Int, name: String, room: String, favorites: Boolean, rec: Boolean, snapshot: String
    ) {
        val realm = Realm.getInstance(config)
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val camera = Camera(
                idBase = idBase,
                name = name,
                room = room,
                favorites = favorites,
                rec = rec,
                snapshot = snapshot
            )
            realmTransaction.insert(camera)
        }
    }
}