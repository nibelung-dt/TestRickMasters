package com.tarasov.testrickmasters.data.network

import retrofit2.http.GET

interface ApiService {

    @GET
    suspend fun getCameras()

    @GET
    suspend fun getDoors()

}

/*
http://cars.cprogroup.ru/api/rubetek/cameras/ - Метод получение камер
http://cars.cprogroup.ru/api/rubetek/doors/ - Метод получение дверей

 */