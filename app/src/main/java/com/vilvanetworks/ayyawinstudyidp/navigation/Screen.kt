package com.vilvanetworks.ayyawinstudyidp.navigation

import com.vilvanetworks.ayyawinstudyidp.objectInterface.AccountData

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
    object PopularListScreen : Screen("popular_list_screen")
    object DetailsScreen : Screen("details_screen")
    object AddToCartScreen : Screen("add_to_cart_screen")
    object TaskScreen : Screen("task_screen")
    object MyAccount : Screen("my_account")
    object Otpscreen : Screen("otp_screen/{screenName}/{mobile}") {
        fun createRoute(screenName: String, mobile: String) = "otp_screen/$screenName/$mobile"
    }

    object Otpscreen1 : Screen("otp_screen1")
    object SignUp : Screen("sign_up")
    object PaymentScreen : Screen("payment_screen")
    object PaymentSuccess : Screen("payment_success")
    object AddressDetails : Screen("address_details/{mobile}"){
        fun createRoute(mobile: String) = "address_details/$mobile"
    }
    object EducationalDetail : Screen("educational_detail")
    object BarcodeCamerScreen : Screen("barcode_cameracreen")

    object ToShipScreen : Screen("toshipscreen")


}
