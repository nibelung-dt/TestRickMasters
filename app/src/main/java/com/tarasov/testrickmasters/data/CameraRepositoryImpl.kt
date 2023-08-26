package com.tarasov.testrickmasters.data

import com.tarasov.testrickmasters.data.network.ApiService
import com.tarasov.testrickmasters.data.network.RetrofitNetwork
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.domain.CameraRepository
import com.tarasov.testrickmasters.domain.entities.CameraEntity

class CameraRepositoryImpl(
   // val retrofit: RetrofitNetwork
    private val service: DepositServiceApi,
    private val requestMaker: RequestMaker,
    private val errorCodeHandler: ResponseCodeMapper,
    private val requestMapper: OrderCardRequestMapper
    ): CameraRepository {

    override suspend fun getCameraRemote(request: CameraEntity): NetworkResponse<Unit> {
        TODO("Not yet implemented")

//        val retrofit = RetrofitNetwork.retrofit
//        RetrofitNetwork.retrofit.ApiService
//        ApiService.retrofit
    }

}