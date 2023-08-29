package com.tarasov.testrickmasters.domain.camera

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse

interface CameraRepository {
    suspend fun getCamera(): NetworkResponse<List<CameraEntity>>
}