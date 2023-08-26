package com.tarasov.testrickmasters.presentation.utils

import com.tarasov.testrickmasters.data.network.utils.ResponseCode

sealed class SimpleState<out T> {
   // object Default : SimpleState<Nothing>()
    object Loading : SimpleState<Nothing>()
    data class Success<out T>(val data: T) : SimpleState<T>() {
        var isHandled = false
    }
    data class Error(val responseCode: ResponseCode) : SimpleState<Nothing>()
}
