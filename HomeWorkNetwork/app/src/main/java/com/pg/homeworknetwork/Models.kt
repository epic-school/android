package com.pg.homeworknetwork

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

//{
//    "page": 1,
//    "per_page": 6,
//    "total": 12,
//    "total_pages": 2,
//    "data": [
//    {
//        "id": 7,
//        "email": "michael.lawson@reqres.in",
//        "first_name": "Michael",
//        "last_name": "Lawson",
//        "avatar": "https://reqres.in/img/faces/7-image.jpg"
//    },
//    ],
//    "support": {
//    "url": "https://reqres.in/#support-heading",
//    "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//}
//}
@Serializable
data class Users(
    @SerialName("page")
    val page: Int,
    @SerialName("data")
    val data: List<User> = ArrayList()
)

//{
//    "data": {
//    "id": 2,
//    "email": "janet.weaver@reqres.in",
//    "first_name": "Janet",
//    "last_name": "Weaver",
//    "avatar": "https://reqres.in/img/faces/2-image.jpg"
//},
//    "support": {
//    "url": "https://reqres.in/#support-heading",
//    "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//}
//}
@Serializable
data class Data(
    @SerialName("data")
    val data: User
)

@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String? = null,
    @SerialName("first_name")
    val first_name: String? = null,
    @SerialName("last_name")
    val last_name: String? = null,
    @SerialName("avatar")
    val avatar: String? = null,
)