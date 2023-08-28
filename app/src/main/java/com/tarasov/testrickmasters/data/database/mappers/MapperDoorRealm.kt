package com.tarasov.testrickmasters.data.database.mappers

import com.tarasov.testrickmasters.data.database.model.DoorRealm
import com.tarasov.testrickmasters.domain.door.DoorEntity
import javax.inject.Inject

class MapperDoorRealm @Inject constructor() {
    fun map(doorRealm: DoorRealm) : DoorEntity {
        return DoorEntity(
            name = doorRealm.name,
            snapshot = doorRealm.snapshot,
            room = doorRealm.room,
            id = doorRealm.baseId,
            favorites = doorRealm.favorites,
        )
    }
}