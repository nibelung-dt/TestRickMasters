package com.tarasov.testrickmasters.data.network.door

import com.tarasov.testrickmasters.domain.door.DoorEntity

class MapperDoorDTO {
    fun map(doorDTO: DoorDTO): DoorEntity {
        return DoorEntity(
            name = doorDTO.name,
            snapshot = doorDTO.snapshot, // ?: "null",  сервер может не прислать этот параметр
            room = doorDTO.room ?: "null",
            id = doorDTO.id,
            favorites = doorDTO.favorites,
        )
    }
}