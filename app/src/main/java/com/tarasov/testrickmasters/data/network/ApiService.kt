package com.tarasov.testrickmasters.data.network

import com.tarasov.testrickmasters.data.network.dto.CameraDTO
import com.tarasov.testrickmasters.data.network.dto.DoorDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cameras")
    suspend fun getCameras(): Response<List<CameraDTO>>

    @GET("doors")
    suspend fun getDoors(): Response<List<DoorDTO>>
}

/*
http://cars.cprogroup.ru/api/rubetek/cameras/ - Метод получение камер
http://cars.cprogroup.ru/api/rubetek/doors/ - Метод получение дверей
 */