package com.tarasov.testrickmasters.data.network.mappers

import com.tarasov.testrickmasters.data.network.dto.CameraDTO
import com.tarasov.testrickmasters.domain.entities.CameraEntity

class MapperCameraDTO(val cameraDTO: CameraDTO) {
    fun map(): CameraEntity {
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