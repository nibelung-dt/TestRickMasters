package com.tarasov.testrickmasters.data.network.camera

data class CameraDTO(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)