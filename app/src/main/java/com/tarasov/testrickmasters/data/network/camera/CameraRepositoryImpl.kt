package com.tarasov.testrickmasters.data.network.camera

import android.util.Log
import com.tarasov.testrickmasters.data.database.model.CameraRealm
import com.tarasov.testrickmasters.data.database.DatabaseOperations
import com.tarasov.testrickmasters.data.database.mappers.MapperCameraRealm
import com.tarasov.testrickmasters.data.network.ApiService
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.data.network.utils.RequestMaker
import com.tarasov.testrickmasters.domain.camera.CameraRepository
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val requestMaker: RequestMaker,
    private val requestMapper: MapperCameraDTO,
    private val mapperCameraRealm: MapperCameraRealm,
    private val databaseOperations: DatabaseOperations
    ): CameraRepository {

    override suspend fun getCamera(): NetworkResponse<List<CameraEntity>> {
        val cachedList = databaseOperations.getCamerasCache()

        if (cachedList.isEmpty()) {
            Log.d("MY_LOG", "cachedList.isEmpty() = true")
            val response = requestMaker.getResponse(
                funToExecute = { service.getCameras() },
                mapResponse = { list ->
                    list.data.cameras.map(requestMapper::map)
                    }
                )
             if (response is NetworkResponse.Success) {
                 response.data.forEach {
                     databaseOperations.addCamera( it )
                 }
             }
            return response

            } else {
                Log.d("MY_LOG", "cachedList.isEmpty() = false")
                return cacheToEntity(cachedList)
            }
    }

    private fun cacheToEntity(cachedList: List<CameraRealm>): NetworkResponse.Success<List<CameraEntity>> {
        val cachedListEntity = mutableListOf<CameraEntity>()
        for(i in cachedList) {
            cachedListEntity.add(mapperCameraRealm.map(i))
        }
       return NetworkResponse.Success(cachedListEntity)
    }
}