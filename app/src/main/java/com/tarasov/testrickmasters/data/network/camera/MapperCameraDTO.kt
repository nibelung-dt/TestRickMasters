package com.tarasov.testrickmasters.data.network.camera


import com.tarasov.testrickmasters.data.database.model.CameraRealm
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import io.realm.Realm
import java.util.UUID
import javax.inject.Inject

class MapperCameraDTO @Inject constructor() {
   // fun map(cameraDTO: CameraDTO): CameraEntity {
    fun map(cameraDTO: CameraDTO): CameraEntity {
        return CameraEntity(
            name = cameraDTO.name,
            snapshot = cameraDTO.snapshot,
            room = cameraDTO.room ?: "null",
            id = cameraDTO.id,
            favorites = cameraDTO.favorites,
            rec = cameraDTO.rec
        )
    }

    fun mapToCameraRealm(cameraInput: CameraDTO): CameraRealm {
        val realm = Realm.getDefaultInstance()
        val camera = realm.createObject(CameraRealm::class.java, UUID.randomUUID().toString())
        camera.name = cameraInput.name
        camera.room = cameraInput.room ?: "null"
        camera.favorites = cameraInput.favorites
        camera.rec = cameraInput.rec
        camera.snapshot = cameraInput.snapshot

        return camera
    }
}

//fun map(cameraDTO: ListCameraDTO): CameraEntity {
//    return CameraEntity(
//        name = cameraDTO.name,
//        snapshot = cameraDTO.snapshot,
//        room = cameraDTO.room,
//        id = cameraDTO.id,
//        favorites = cameraDTO.favorites,
//        rec = cameraDTO.rec
//    )
//}