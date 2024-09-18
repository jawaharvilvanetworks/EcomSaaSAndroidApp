package com.vilvanetworks.ayyawinstudyidp.objectInterface

data class LoginResponse(
    val statuscode: Int,
    val success: Boolean,
    val message: String,
    val storeid: Int,
    val token: String,
    val userid: Int,
    val username: String,
    val roleid: Int,
    val rolename: String
)