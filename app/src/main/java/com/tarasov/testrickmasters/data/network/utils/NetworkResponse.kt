package com.tarasov.testrickmasters.data.network.utils

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(
        val responseCode: ResponseCode,
        val data: Any? = null
    ) : NetworkResponse<Nothing>()
}
