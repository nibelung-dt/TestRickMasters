package com.tarasov.testrickmasters.domain.door

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.domain.camera.CameraEntity

interface InteractorDoors {

    suspend fun getDoorEntity(): NetworkResponse<List<DoorEntity>>
}