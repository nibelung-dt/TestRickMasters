package com.tarasov.testrickmasters.domain.camera

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse

interface CameraRepository {
//  request: CameraEntity
    suspend fun getCameraRemote(): NetworkResponse<List<CameraEntity>>
}