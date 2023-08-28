package com.tarasov.testrickmasters.data.database


import com.tarasov.testrickmasters.data.database.model.CameraRealm
import com.tarasov.testrickmasters.data.database.model.DoorRealm
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.domain.door.DoorEntity
import io.realm.Realm
import java.util.UUID
import javax.inject.Inject

// https://www.section.io/engineering-education/using-realm-database-in-android/

class DatabaseOperations @Inject constructor () {

    // можно так realm.executeTransactionAwait(Dispatchers.IO)
    fun addCamera(cameraInput: CameraEntity) {
        //    val realm = Realm.getInstance(config)
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { r: Realm ->
            val camera = r.createObject(CameraRealm::class.java, UUID.randomUUID().toString())
            camera.name = cameraInput.name
            camera.room = cameraInput.room
            camera.favorites = cameraInput.favorites
            camera.rec = cameraInput.rec
            camera.snapshot = cameraInput.snapshot

            realm.insertOrUpdate(camera)
        }
    }

    // fun getCamerasCache(): MutableLiveData<List<Camera>> {
    fun getCamerasCache(): List<CameraRealm> {
        // val realm = Realm.getInstance(config)
        val realm = Realm.getDefaultInstance()
        //var list = listOf<Camera>()
        val notes = realm.where(CameraRealm::class.java).findAll()
        val list = notes?.subList(0, notes.size)!!
        return list
    }

    fun addDoor(doorInput: DoorEntity) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { r: Realm ->
            val door = r.createObject(DoorRealm::class.java, UUID.randomUUID().toString())
            door.name = doorInput.name
            door.room = doorInput.room
            door.favorites = doorInput.favorites
            door.snapshot = doorInput.snapshot

            realm.insertOrUpdate(door)
        }
    }

    fun getDoorsCache(): List<DoorRealm> {
        val realm = Realm.getDefaultInstance()
        val notes = realm.where(DoorRealm::class.java).findAll()
        val list = notes?.subList(0, notes.size)!!
        return list
    }
}