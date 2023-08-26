package com.tarasov.testrickmasters.data.network.utils

import com.tarasov.testrickmasters.R


const val UNDEFINED = -1
const val INTERNET_ACCESS_DENIED = -2
const val BAD_REQUEST = 400
const val NOT_FOUND = 404

sealed class ResponseCode() {
    abstract val code: Int
    abstract val titleResId: Int
   // abstract val subtitleResId: Int
   // abstract val dialogTitleResId: Int


    object Undefined : ResponseCode() {
        override val code: Int = UNDEFINED
        override val titleResId: Int = R.string.response_code_undefined
    }

    object INTERNET_ACCESS_DENIED : ResponseCode() {
        override val code: Int = UNDEFINED
        override val titleResId: Int = R.string.response_code_internet_denied
    }

    object BAD_REQUEST : ResponseCode() {
        override val code: Int = UNDEFINED
        override val titleResId: Int = R.string.response_code_bad_request
    }

    object NOT_FOUND : ResponseCode() {
        override val code: Int = UNDEFINED
        override val titleResId: Int = R.string.response_code_not_found
    }
}
