package com.tarasov.testrickmasters.domain.camera

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse

class InteractorCamerasImpl(
    private val repository: CameraRepository
    ): InteractorCameras {
    override suspend fun getCameraEntity(): NetworkResponse<List<CameraEntity>> {
        return repository.getCameraRemote()
    }
}