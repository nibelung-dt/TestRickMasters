package com.tarasov.testrickmasters.data.network.door

import com.tarasov.testrickmasters.domain.door.DoorEntity
import javax.inject.Inject

class MapperDoorDTO @Inject constructor() {
    fun map(doorDTO: DoorDTO): DoorEntity {
        return DoorEntity(
            name = doorDTO.name,
            snapshot = doorDTO.snapshot ?: "null",
            room = doorDTO.room ?: "null",
            id = doorDTO.id,
            favorites = doorDTO.favorites,
        )
    }
}