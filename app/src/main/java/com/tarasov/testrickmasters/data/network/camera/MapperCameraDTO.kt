package com.tarasov.testrickmasters.data.network.camera

import com.tarasov.testrickmasters.domain.camera.CameraEntity

class MapperCameraDTO() {
    fun map(cameraDTO: CameraDTO): CameraEntity {
        return CameraEntity(
            name = cameraDTO.name,
            snapshot = cameraDTO.snapshot,
            room = cameraDTO.room,
            id = cameraDTO.id,
            favorites = cameraDTO.favorites,
            rec = cameraDTO.rec
        )
    }
}