package com.tarasov.testrickmasters.data.database.mappers

import com.tarasov.testrickmasters.data.database.model.CameraRealm
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import javax.inject.Inject

class MapperCameraRealm @Inject constructor() {
    fun map(cameraRealm: CameraRealm) : CameraEntity {
        return CameraEntity(
            name = cameraRealm.name,
            snapshot = cameraRealm.snapshot,
            room = cameraRealm.room,
            id = cameraRealm.baseId,
            favorites = cameraRealm.favorites,
            rec = cameraRealm.rec
        )
    }

//    fun mapEntityToRealm(cameraInput: CameraEntity) : CameraRealm {
//        val realm = Realm.getDefaultInstance()
//        val camera = realm.createObject(CameraRealm::class.java, UUID.randomUUID().toString())
//        camera.name = cameraInput.name
//        camera.room = cameraInput.room ?: "null"
//        camera.favorites = cameraInput.favorites
//        camera.rec = cameraInput.rec
//        camera.snapshot = cameraInput.snapshot
//
//        return camera
//    }
}