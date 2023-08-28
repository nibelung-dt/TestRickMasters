package com.tarasov.testrickmasters.data.network.door

data class DoorDTO(
    val name: String,
    val snapshot: String?,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
)

/*

"name": "Door Door, Door Door",
      "snapshot": "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png",
      "room": "FIRST",
      "id": 6,
      "favorites": true
 */
