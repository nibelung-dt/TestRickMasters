package com.tarasov.testrickmasters.data.network.door

import com.tarasov.testrickmasters.data.network.ApiService
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.data.network.utils.RequestMaker
import com.tarasov.testrickmasters.domain.door.DoorEntity
import com.tarasov.testrickmasters.domain.door.DoorRepository
import javax.inject.Inject

class DoorRepositoryImpl @Inject constructor (
    private val service: ApiService,
    private val requestMaker: RequestMaker,
    private val requestMapper: MapperDoorDTO
): DoorRepository {

    suspend fun test() {
        service.getDoors()
    }

    override suspend fun getDoorRemote(): NetworkResponse<List<DoorEntity>> {
        test()
        return requestMaker.getResponse(
            funToExecute = { service.getDoors() },
            mapResponse = { list -> list.data.map(requestMapper::map) }
        )
    }
}