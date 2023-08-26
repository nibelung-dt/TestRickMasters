package com.tarasov.testrickmasters.data.network.camera

import android.util.Log
import com.tarasov.testrickmasters.data.network.ApiService
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.data.network.utils.RequestMaker
import com.tarasov.testrickmasters.domain.camera.CameraRepository
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.presentation.utils.SimpleState
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val requestMaker: RequestMaker,
   // private val errorCodeHandler: ResponseCodeMapper,
    private val requestMapper: MapperCameraDTO
    ): CameraRepository {

    override suspend fun getCameraRemote(): NetworkResponse<List<CameraEntity>> {
        val x = requestMaker.getResponse(
            funToExecute = { service.getCameras2() },
            mapResponse = { list ->  Log.d("MY_LOG", list.data.second.toString()) }
           // mapResponse = { list -> list.data.map(requestMapper::map) }
        )
        Log.d("MY_LOG", "repo")
        Log.d("MY_LOG", x.toString())

        return requestMaker.getResponse(
            funToExecute = { service.getCameras2() },
            mapResponse = { list -> list.data.second.map(requestMapper::map) }
        )


//        return requestMaker.getResponse(
//            funToExecute = { service.getCameras() },
//            mapResponse = { list -> list.map(requestMapper::map) }
//        )


    }
}