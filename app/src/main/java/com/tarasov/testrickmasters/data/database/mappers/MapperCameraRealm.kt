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
}