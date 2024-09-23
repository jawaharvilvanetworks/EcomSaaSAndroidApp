package com.vilvanetworks.ayyawinstudyidp.utils

import androidx.annotation.InspectableProperty

object SharedPrefKey {
    lateinit var appName: InspectableProperty.EnumEntry
    var applicationName: String = "JetComposeLoginUI"
    var accessToken: String = "accessToken"
    var storeid: String = "storeid"
    var orderid: String = "orderid"
    var barcodeno: String = "barcodeno"

    var paymentStatus: String = "paymentStatus"
}