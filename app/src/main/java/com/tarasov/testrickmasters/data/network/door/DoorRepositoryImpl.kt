package com.tarasov.testrickmasters.data.network.door

import android.util.Log
import com.tarasov.testrickmasters.data.database.DatabaseOperations
import com.tarasov.testrickmasters.data.database.mappers.MapperCameraRealm
import com.tarasov.testrickmasters.data.database.mappers.MapperDoorRealm
import com.tarasov.testrickmasters.data.database.model.CameraRealm
import com.tarasov.testrickmasters.data.database.model.DoorRealm
import com.tarasov.testrickmasters.data.network.ApiService
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.data.network.utils.RequestMaker
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.domain.door.DoorEntity
import com.tarasov.testrickmasters.domain.door.DoorRepository
import javax.inject.Inject

class DoorRepositoryImpl @Inject constructor (
    private val service: ApiService,
    private val requestMaker: RequestMaker,
    private val requestMapper: MapperDoorDTO,
    private val mapperDoorRealm: MapperDoorRealm,
    private val databaseOperations: DatabaseOperations
): DoorRepository {

//    suspend fun test() {
//        service.getDoors()
//    }



    override suspend fun getDoor(): NetworkResponse<List<DoorEntity>> {
        val cachedList = databaseOperations.getDoorsCache()
        if (cachedList.isEmpty()) {
            Log.d("MY_LOG", "cached Doors = true")
            val response = requestMaker.getResponse(
                funToExecute = { service.getDoors() },
                mapResponse = { list -> list.data.map(requestMapper::map) }
            )
            if (response is NetworkResponse.Success) {
                response.data.forEach {
                    databaseOperations.addDoor(it)
                }
            }
            return response
        } else {
            Log.d("MY_LOG", "cached Doors = false")
            return cacheToEntity(cachedList)
        }
    }

    private fun cacheToEntity(cachedList: List<DoorRealm>): NetworkResponse.Success<List<DoorEntity>> {
        val cachedListEntity = mutableListOf<DoorEntity>()
        for(i in cachedList) {
            cachedListEntity.add(mapperDoorRealm.map(i))
        }
        return NetworkResponse.Success(cachedListEntity)
    }
}