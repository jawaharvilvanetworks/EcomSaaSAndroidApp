package com.vilvanetworks.ayyawinstudyidp.objectInterface

import kotlinx.serialization.Serializable

data class SuccessResponseMyAccountFetch(
    val code: Int,
    val message: String,
    val data: AccountData
)

// Define the nested data class for the "data" field
data class AccountData(
    val id: Int? = 0,
    val name: String? = "",
    val email: String? = "",
    val type: Int? = 0,
    val university: String? = "",
    val education: String? = "",
    val address1: String? = "",
    val address2: String? = "",
    val city: String? = "",
    val district: String? = "",
    val state: String? = "",
    val mobile: String? = "",
    val institute: String? = "",
    val id_card_photo: String? = "",
    val payment: Int? = 0,
    val payment_status: String? = "",
)