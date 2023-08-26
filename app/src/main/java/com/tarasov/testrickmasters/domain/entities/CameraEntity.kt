package com.tarasov.testrickmasters.domain.entities

data class CameraEntity(
    val name: String,
    val snapshot: String,
    val room: String,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)