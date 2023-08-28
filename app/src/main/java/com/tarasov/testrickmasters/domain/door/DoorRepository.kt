package com.tarasov.testrickmasters.domain.door

import com.tarasov.testrickmasters.data.network.utils.NetworkResponse

interface DoorRepository {
    suspend fun getDoor(): NetworkResponse<List<DoorEntity>>
}