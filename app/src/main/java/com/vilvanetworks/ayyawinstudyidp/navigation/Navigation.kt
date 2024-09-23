package com.vilvanetworks.ayyawinstudyidp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vilvanetworks.ayyawinstudyidp.view.*
import de.alexander13oster.easycomposebarcodescanner.CameraScreen

@Composable
fun Navigation(paymentsuccss: Boolean = false) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.BarcodeCamerScreen.route) {
            CameraScreen(navController = navController)
        }

        composable(Screen.ToShipScreen.route) {
            ToShipScreen(navController = navController)
        }



        composable(Screen.AddressDetails.route, arguments = listOf(navArgument("mobile") { type = NavType.StringType})) {backStackEntry ->
            val mobile = backStackEntry.arguments?.getString("mobile")
            AddressDetails(navController = navController, mobile)
        }

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.PopularListScreen.route) {
            PopularListScreen(navController = navController)
        }
        composable(Screen.DetailsScreen.route) {
            FlowerDetailsScreen(navController = navController)
        }
        composable(Screen.AddToCartScreen.route) {
            CheckoutScreen()
        }
        composable(Screen.TaskScreen.route) {
            TaskScreen(navController = navController)
        }

        composable(Screen.MyAccount.route) {
            MyAccount(navController = navController)
        }
        composable(Screen.Otpscreen.route, arguments = listOf(navArgument("screenName") { type = NavType.StringType}, navArgument("mobile") { type = NavType.StringType})) {backStackEntry ->
            val screenName = backStackEntry.arguments?.getString("screenName")
            val mobile = backStackEntry.arguments?.getString("mobile")
            OtpScreen(navController = navController, screenName, mobile)
        }
        composable(Screen.Otpscreen1.route) {
            OtpScreen1(navController = navController)
        }
        composable(Screen.SignUp.route) {
            SignUp(navController = navController)
        }
        composable(Screen.PaymentScreen.route) {
            PaymentScreen(navController = navController)
        }

        composable(Screen.PaymentSuccess.route) {
            PaymentSuccess()
        }

        composable(Screen.EducationalDetail.route) {
            EducationalDetail()
        }

    }
}