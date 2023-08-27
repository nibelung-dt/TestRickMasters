package com.tarasov.testrickmasters.data.network.camera

import com.tarasov.testrickmasters.domain.camera.CameraEntity
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