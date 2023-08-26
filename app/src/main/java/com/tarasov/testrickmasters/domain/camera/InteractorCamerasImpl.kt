package com.tarasov.testrickmasters.domain.camera

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import javax.inject.Inject

class InteractorCamerasImpl @Inject constructor (
    private val repository: CameraRepository
    ): InteractorCameras {
    override suspend fun getCameraEntity(): NetworkResponse<List<CameraEntity>> {
        return repository.getCameraRemote()
    }
}