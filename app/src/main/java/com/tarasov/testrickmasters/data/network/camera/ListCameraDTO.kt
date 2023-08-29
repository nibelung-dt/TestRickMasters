package com.tarasov.testrickmasters.data.network.camera

data class ListCameraDTO(
    val success: Boolean,
    val data: Data
) {
    data class Data(
        val room: List<String>,
        val cameras: List<CameraDTO>
    )
}

// http://cars.cprogroup.ru/api/rubetek/cameras/