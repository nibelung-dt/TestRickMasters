package com.tarasov.testrickmasters.domain.camera

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse

interface InteractorCameras {
    suspend fun getCameraEntity(): NetworkResponse<List<CameraEntity>>
}