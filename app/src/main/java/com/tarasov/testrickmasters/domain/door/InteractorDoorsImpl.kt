package com.tarasov.testrickmasters.domain.door

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import javax.inject.Inject

class InteractorDoorsImpl @Inject constructor(
    private val repository: DoorRepository
): InteractorDoors {
    override suspend fun getDoorEntity(): NetworkResponse<List<DoorEntity>> {
        return repository.getDoor()
    }
}