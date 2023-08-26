package com.tarasov.testrickmasters.data.network.mappers

import com.tarasov.testrickmasters.data.network.dto.DoorDTO
import com.tarasov.testrickmasters.domain.entities.DoorEntity

class MapperDoorDTO(val doorDTO: DoorDTO) {
    fun map(): DoorEntity {
        return DoorEntity(
            name = doorDTO.name,
            snapshot = doorDTO.snapshot, // ?: "null",  сервер может не прислать этот параметр
            room = doorDTO.room ?: "null",
            id = doorDTO.id,
            favorites = doorDTO.favorites,
        )
    }
}