package com.tarasov.testrickmasters.domain.door

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse

class InteractorDoorsImpl(
    private val repository: DoorRepository
): InteractorDoors {
    override suspend fun getDoorEntity(): NetworkResponse<List<DoorEntity>> {
        return repository.getDoorRemote()
    }
}