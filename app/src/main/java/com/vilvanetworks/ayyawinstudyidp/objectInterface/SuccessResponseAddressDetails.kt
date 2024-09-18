package com.vilvanetworks.ayyawinstudyidp.objectInterface

import kotlinx.serialization.Serializable

data class SuccessResponseAddressDetails(
    val code: Int,
    val message: String,
    var data: UserAuthRespData
)
