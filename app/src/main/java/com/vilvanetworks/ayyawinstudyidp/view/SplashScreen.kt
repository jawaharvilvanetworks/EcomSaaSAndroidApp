package com.vilvanetworks.ayyawinstudyidp.view

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.utils.Constants
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(2f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }
            )
        )
        delay(Constants.SPLASH_SCREEN_DURATION)
        navController.popBackStack()
//       navController.navigate(Screen.AddressDetails.route)
//        Log.d("SharedPrefKey : " , PrefHelper().getStringFromShared(SharedPrefKey.paymentStatus ).toString())
//        Log.d("SharedPrefKey : " , PrefHelper().getStringFromShared(SharedPrefKey.accessToken ).toString())

        val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
        if (accessToken.isNullOrEmpty()) {
            navController.navigate(Screen.LoginScreen.route)
        }
        else{

            navController.navigate(Screen.HomeScreen.route)
//            if(PrefHelper().getStringFromShared(SharedPrefKey.paymentStatus ).toString() == "FAILED")
//                navController.navigate(Screen.PaymentScreen.route)
//            else if(PrefHelper().getStringFromShared(SharedPrefKey.paymentStatus ).toString() === "SUCCESS")
//                navController.navigate(Screen.HomeScreen.route)
//            else if(PrefHelper().getStringFromShared(SharedPrefKey.paymentStatus ).toString() === "PENDING")
//                navController.navigate(Screen.HomeScreen.route)
//            else if(PrefHelper().getStringFromShared(SharedPrefKey.paymentStatus ).toString() === "")
//                navController.navigate(Screen.LoginScreen.route)
//            else
//                navController.navigate(Screen.HomeScreen.route)
        }




    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "Splash bg",
            contentScale = ContentScale.FillWidth
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize().padding(70.dp)
            )
        }

    }

}

@Preview
@Composable
fun SplashScreenPreview() = SplashScreen(NavController(LocalContext.current))