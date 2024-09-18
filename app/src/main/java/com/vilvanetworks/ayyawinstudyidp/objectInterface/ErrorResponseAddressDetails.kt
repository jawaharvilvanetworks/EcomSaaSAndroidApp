package com.vilvanetworks.ayyawinstudyidp.objectInterface

import kotlinx.serialization.Serializable

data class ErrorResponseAddressDetails(
    val code: Int,
    val message: String,
    val errors: Errors
)

@Serializable
data class Errors(
    val id_card_photo: List<String>
)