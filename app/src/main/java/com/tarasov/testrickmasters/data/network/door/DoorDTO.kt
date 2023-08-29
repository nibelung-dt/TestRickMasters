package com.tarasov.testrickmasters.data.network.door

data class DoorDTO(
    val name: String,
    val snapshot: String?,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
)