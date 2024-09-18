package com.vilvanetworks.ayyawinstudyidp.view

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.ui.theme.ghost_white
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey



@Composable
fun MyAccount(navController: NavController) {

//    val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
//    if(accessToken == "" || accessToken == null){
//        navController.popBackStack()
//        navController.navigate(Screen.SplashScreen.route)
//        return;
//    } else {
//        accountdata  =  getAccountDataHomeScreen()
//    }

    val ctx = LocalContext.current

//    Log.d("mresponse:","Code: , Message: ${accountdata?.id}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.address1}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.address2}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.city}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.district}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.education}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.email}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.institute}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.mobile}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.payment}")
//    Log.d("mresponse:","Code: , Message: ${accountdata?.payment_status}")
//

    
    Column(
        modifier = Modifier.fillMaxWidth() .background(color = ghost_white),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        // Name input field
//        val nameState = remember { mutableStateOf(accountdata?.name!!) }
//        OutlinedTextField(
//            value = nameState.value,
//            onValueChange = { nameState.value = it },
//            label = { Text("Name") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        // Email input field
//        val emailState = remember { mutableStateOf(accountdata?.email!!) }
//        OutlinedTextField(
//            value = emailState.value,
//            onValueChange = { emailState.value = it },
//            label = { Text("Email") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        // Mobile Number input field
//        val mobileState = remember { mutableStateOf(accountdata?.mobile!!) }
//        OutlinedTextField(
//            value = mobileState.value,
//            onValueChange = { mobileState.value = it },
//            label = { Text("Mobile Number") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        val typestatus = if (accountdata?.type!!.toString() == "1") "Student" else "Volunteer"
//        // Type input field
//        val typeState = remember { mutableStateOf(typestatus.toString()) }
//        OutlinedTextField(
//            value = typeState.value,
//            onValueChange = { typeState.value = it },
//            label = { Text("Type") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        // City input field
//        val cityState = remember { mutableStateOf(accountdata?.city!!) }
//        OutlinedTextField(
//            value = cityState.value,
//            onValueChange = { cityState.value = it },
//            label = { Text("City") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        // District input field
//        val districtState = remember { mutableStateOf(accountdata?.district!!) }
//        OutlinedTextField(
//            value = districtState.value,
//            onValueChange = { districtState.value = it },
//            label = { Text("District") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        // School / College / Institute Name input field
//        val schoolState = remember { mutableStateOf(accountdata?.education!!) }
//        OutlinedTextField(
//            value = schoolState.value,
//            onValueChange = { schoolState.value = it },
//            label = { Text("School / College / Institute Name") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//
////        val paymentState = remember { mutableStateOf(accountdata?.payment.toString()) }
////        OutlinedTextField(
////            value = paymentState.value,
////            onValueChange = { paymentState.value = it },
////            label = { Text("Payment") },
////            enabled = false,
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(horizontal = 16.dp)
////        )
//
//        val paymentstatusState = remember { mutableStateOf(accountdata?.payment_status.toString()) }
//        OutlinedTextField(
//            value = paymentstatusState.value,
//            onValueChange = { paymentstatusState.value = it },
//            label = { Text("Payment Status") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//
//
//        // ID Card / Passport Size Photo input field
//        val idState = remember { mutableStateOf(accountdata?.id_card_photo!!) }
//        OutlinedTextField(
//            value = idState.value,
//            onValueChange = { idState.value = it },
//            label = { Text("ID Card / Passport Size Photo") },
//            enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        ClickableText(
//            text = AnnotatedString("View Photo"),
//            onClick = {
//                // Open the hyperlink using an appropriate intent
//                try{
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.ayyawinstudyfoundation.org/"+idState.value))
//                    ctx.startActivity(intent)
//                } catch (e: Exception) {
//                    Toast.makeText(ctx,"URL Open Failed", Toast.LENGTH_SHORT).show()
//                    // on below line we are handling exception.
//                }
//
//            },
//            style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
//        )
//
//        Button(
//            onClick = { /* Handle button click */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(0xFF006FFD),
//                contentColor = Color.White
//            )
//        ) {
//            Text(text = "Start & Update ")
//        }
        Button(
            onClick = {
                //PrefHelper().setStringToShared(SharedPrefKey.accessToken, "" )

                navController.popBackStack()
                navController.navigate(Screen.LoginScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF0000),
                contentColor = Color.White
            )
        ) {
            Text(text = "Log Out")
        }

//        val passwordState = remember { mutableStateOf("") }
//        OutlinedTextField(
//            value = passwordState.value,
//            onValueChange = { passwordState.value = it },
//            label = { Text("Password") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        val cPasswordState = remember { mutableStateOf("") }
//        OutlinedTextField(
//            value = cPasswordState.value,
//            onValueChange = { cPasswordState.value = it },
//            label = { Text("Confirm Password") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//        Button(
//            onClick = { /* Handle button click */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(0xFF00FF00),
//                        contentColor = Color.White
//            )
//        ) {
//            Text(text = "Update Password")
//        }
        // Add more fields as needed
    }
}
