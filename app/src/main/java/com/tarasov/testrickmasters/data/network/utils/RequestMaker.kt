package com.tarasov.testrickmasters.data.network.utils

import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RequestMaker @Inject constructor(
    private val responseCodeMapper: ResponseCodeMapper
    ) {

    suspend fun <ResNet, ResDom> getResponse(
        funToExecute: suspend () -> Response<ResNet>,
        mapResponse: (ResNet) -> ResDom,
        errorCodeHandler: ResponseCodeMapper? = null
    ): NetworkResponse<ResDom> {

        responseCodeMapper.customCodeMapper = errorCodeHandler

        return try {
            val response = funToExecute()
            if (response.isSuccessful) {
                NetworkResponse.Success(data = mapResponse(requireNotNull(response.body())))
            } else {
                NetworkResponse.Error(responseCode = responseCodeMapper.map(response.code()))
            }
        } catch (e: IOException) {
            NetworkResponse.Error(responseCode = responseCodeMapper.map(INTERNET_ACCESS_DENIED))
        } catch (e: Exception) {
            NetworkResponse.Error(responseCode = responseCodeMapper.map(UNDEFINED))
        }
    }
}