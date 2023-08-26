package com.tarasov.testrickmasters.domain

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.domain.entities.CameraEntity

interface CameraRepository {

    suspend fun getCameraRemote(
        request: CameraEntity
    ): NetworkResponse<Unit>
}