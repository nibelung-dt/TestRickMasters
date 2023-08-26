package com.tarasov.testrickmasters.data.network.camera

data class CameraDTO(
    val name: String,
    val snapshot: String,
    val room: String,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)

data class ListCameraDTO(
    val success: Boolean,
  //  val data: List<E>
    //val data: Pair<Room, ListCameras>>
   // val data: Pair<List<String>, List<CameraDTO>>
)



//val cameras: List<CameraDTO>,
// val rooms: List<String>,

/*
        "name": "Camera 1",
        "snapshot": "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png",
        "room": "FIRST",
        "id": 1,
        "favorites": true,
        "rec": false
 */