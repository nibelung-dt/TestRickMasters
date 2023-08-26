package com.tarasov.testrickmasters.data.network.camera

data class CameraDTO(
    val name: String,
    val snapshot: String,
    val room: String,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)


/*
        "name": "Camera 1",
        "snapshot": "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png",
        "room": "FIRST",
        "id": 1,
        "favorites": true,
        "rec": false
 */