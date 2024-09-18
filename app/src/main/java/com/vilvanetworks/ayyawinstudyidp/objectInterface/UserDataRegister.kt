package com.vilvanetworks.ayyawinstudyidp.objectInterface

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

data class UserDataRegister(
    @Part
    val type: String,
    @Part
    val university: String,
    @Part
    val city: String,
    @Part
    val district: String,
    @Part
    val state: String,
    @Part
    val education: String,
    @Part
    val name: String,
    @Part
    val mobile: String,
    @Part
    val email: String,
    @Part
    val institute: String,
    @Part
    val id_card_photo: MultipartBody.Part,
    @Part("id_card_photo")
    val requestBody: RequestBody,
    @Part
    val address1: String,
    @Part
    val address2: String,
)
//
//--form 'type="1"' \
//--form 'university="Anna University"' \
//--form 'city="Chennai"' \
//--form 'district="Chennai"' \
//--form 'state="TamilNadu"' \
//--form 'education="BCA"' \
//--form 'name="Idp Tech"' \
//--form 'mobile="8122512122"' \
//--form 'email="user1111@idp.com"' \
//--form 'institute="IDP"' \
//--form 'id_card_photo=@"/Users/gopinathravirajan/Downloads/interface-success-01-2.png"' \
//--form 'address1="asdf"' \
//--form 'address2="sdfsadf"'