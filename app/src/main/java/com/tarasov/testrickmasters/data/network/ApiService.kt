package com.tarasov.testrickmasters.data.network

import com.tarasov.testrickmasters.data.network.camera.CameraDTO
import com.tarasov.testrickmasters.data.network.camera.ListCameraDTO
import com.tarasov.testrickmasters.data.network.door.DoorDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cameras")
   suspend fun getCameras(): Response<List<CameraDTO>>
// suspend fun getCameras(): Response<ListCameraDTO>
    @GET("doors")
    suspend fun getDoors(): Response<List<DoorDTO>>


    @GET("cameras")
    suspend fun getCameras2(): Response<ListCameraDTO>
}

/*
http://cars.cprogroup.ru/api/rubetek/cameras/ - Метод получение камер
http://cars.cprogroup.ru/api/rubetek/doors/ - Метод получение дверей
 */