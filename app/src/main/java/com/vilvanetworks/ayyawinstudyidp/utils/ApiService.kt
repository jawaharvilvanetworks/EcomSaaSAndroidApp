package com.vilvanetworks.ayyawinstudyidp.utils

import com.vilvanetworks.ayyawinstudyidp.objectInterface.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST(AppKeys.SIGNIN)
    fun signin(@Body user: Any): Call<ResponseBody>

    @FormUrlEncoded
    @POST(AppKeys.DASHBOARD)
    fun getDashboard(
        @Header("Authorization") token: String,
        @Field("storeid") storeId: String
    ): Call<DashboardResponse>

    @FormUrlEncoded
    @POST(AppKeys.ORDERS)
    fun getOrders(
        @Header("Authorization") token: String,
        @Field("storeid") storeId: String,
        @Field("orderstatus") orderStatus: String
    ): Call<OrderResponse>

    @POST(AppKeys.VERIFYOTP)
    fun verifyotp(@Body user: Any): Call<UserAuthResp>

    @POST(AppKeys.SIGNUPREQUESTOTP)
    fun signuprequestotp(@Body user: Any): Call<ResponseBody>

    @POST(AppKeys.SIGNUPSUBMITOTP)
    fun signupsubmitotp(@Body user: Any): Call<RespData>

//    @Multipart
//    @POST("api/signup/details/submit")
//    fun signupdetailssubmit(user: UserDataRegister): Call<ResponseBody>



    @Multipart
    @POST(AppKeys.SIGNUPDETAILSSUBMIT)
    fun signupdetailssubmit(@Part("type")
                            type: RequestBody,
                            @Part("university")
                            university: RequestBody,
                            @Part("city")
                            city: RequestBody,
                            @Part("district")
                            district: RequestBody,
                            @Part("state")
                            state: RequestBody,
                            @Part("education")
                            education: RequestBody,
                            @Part("name")
                            name: RequestBody,
                            @Part("mobile")
                            mobile: RequestBody,
                            @Part("email")
                            email: RequestBody,
                            @Part("institute")
                            institute: RequestBody,
                            @Part("id_card_photo")
                            id_card_photo: RequestBody,
                            @Part("address1")
                            address1: RequestBody,
                            @Part("address2")
                            address2: RequestBody): Call<ResponseBody>


    @Multipart
    @POST(AppKeys.SIGNUPDETAILSSUBMIT)
    fun uploadDatasignupdetailssubmit(
        @Part("type") type: RequestBody,
        @Part("university") university: RequestBody,
        @Part("city") city: RequestBody,
        @Part("district") district: RequestBody,
        @Part("state") state: RequestBody,
        @Part("education") education: RequestBody,
        @Part("name") name: RequestBody,
        @Part("mobile") mobile: RequestBody,
        @Part("email") email: RequestBody,
        @Part("institute") institute: RequestBody,
        @Part id_card_photo: MultipartBody.Part,
        @Part("address1") address1: RequestBody,
        @Part("address2") address2: RequestBody
    ): Call<ResponseBody>


    @Multipart
    @POST(AppKeys.TASKUPDATE)
    fun uploadDatataskupdatedetailssubmit(
        @Path("id") taskId: Int,
        @Part("status") status: RequestBody,
        @Part("description") description: RequestBody,
        @Part media_gallery: List<MultipartBody.Part?>,
        @Header("Authorization") token: String
    ): Call<ResponseBody>



    @Multipart
    @POST(AppKeys.SIGNUPDETAILSSUBMIT)
    fun signupdetailssubmit1( body: RequestBody): Call<ResponseBody>



    @POST("api/payment/haodapay")
    fun haodapaysubmit(@Header("Authorization") token: String, @Body user: Any): Call<PaymentAuthResp>

    @GET("api/tasks")
    fun mytasksdetails(@Header("Authorization") token: String): Call<ResponseBody>

    @POST("api/myaccount")
    fun myaccountdetails(@Header("Authorization") token: String): Call<ResponseBody>

    @POST("api/check/paymentstatus")
    fun paymentstatuscheck(@Header("Authorization") token: String): Call<ResponseBody>




}