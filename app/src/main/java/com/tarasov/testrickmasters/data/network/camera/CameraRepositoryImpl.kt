package com.tarasov.testrickmasters.data.network.camera

import com.tarasov.testrickmasters.data.network.ApiService
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.data.network.utils.RequestMaker
import com.tarasov.testrickmasters.domain.camera.CameraRepository
import com.tarasov.testrickmasters.domain.camera.CameraEntity

class CameraRepositoryImpl(
    private val service: ApiService,
    private val requestMaker: RequestMaker,
   // private val errorCodeHandler: ResponseCodeMapper,
    private val requestMapper: MapperCameraDTO
    ): CameraRepository {

    override suspend fun getCameraRemote(): NetworkResponse<List<CameraEntity>> {
        return requestMaker.getResponse(
            funToExecute = { service.getCameras() },
            mapResponse = { list -> list.map(requestMapper::map) }
        )
    }
}