package com.tarasov.testrickmasters.data.network

import com.tarasov.testrickmasters.data.network.camera.ListCameraDTO
import com.tarasov.testrickmasters.data.network.door.ListDoorDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cameras")
    suspend fun getCameras(): Response<ListCameraDTO>

    @GET("doors")
    suspend fun getDoors(): Response<ListDoorDTO>
}