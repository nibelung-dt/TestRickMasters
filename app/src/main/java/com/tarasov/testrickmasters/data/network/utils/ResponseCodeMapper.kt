package com.tarasov.testrickmasters.data.network.utils


open class ResponseCodeMapper() {

    var customCodeMapper: ResponseCodeMapper? = null

    open fun map(code: Int): ResponseCode {
        return when (code) {
            INTERNET_ACCESS_DENIED -> ResponseCode.INTERNET_ACCESS_DENIED
            BAD_REQUEST -> ResponseCode.BAD_REQUEST
            NOT_FOUND -> ResponseCode.NOT_FOUND
            else -> customCodeMapper?.map(code) ?: ResponseCode.Undefined
        }
    }
}