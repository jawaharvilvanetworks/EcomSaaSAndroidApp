package com.vilvanetworks.ayyawinstudyidp.view

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.objectInterface.RespData
import com.vilvanetworks.ayyawinstudyidp.objectInterface.User
import com.vilvanetworks.ayyawinstudyidp.objectInterface.UserAuthResp
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OtpScreen(navController: NavController, screenName: String?, mobile: String?) {
    val firaSansFamily = FontFamily(
        Font(R.font.dmsansregular, FontWeight.Normal),
        Font(R.font.dmsansmedium, FontWeight.Medium),
        Font(R.font.dmsansbold, FontWeight.Bold),
    )

    var otpnumber1 by remember { mutableStateOf("") }
    var otpnumber2 by remember { mutableStateOf("") }
    var otpnumber3 by remember { mutableStateOf("") }
    var otpnumber4 by remember { mutableStateOf("") }
    var otpnumber5 by remember { mutableStateOf("") }
    var otpnumber6 by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusRequester3 = FocusRequester()
    val focusRequester4 = FocusRequester()
    val focusRequester5 = FocusRequester()
    val focusRequester6 = FocusRequester()

    var showhidespinner by remember { mutableStateOf(false) }
    var otpreqerror by remember { mutableStateOf("Otp verification failed, please try again later.") }
    var showhideotpreq by remember { mutableStateOf(false) }

    if (screenName != null) {
        Log.e("screenName", screenName)
    }
    if (mobile != null) {
        Log.e("mobile", mobile)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = ghost_white)
                    .height(100.dp)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 100.dp)) {
                HeaderOtpScreen()
            }

        }
    }

    Column(
        modifier = Modifier
            .background(color = ghost_white)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "Enter confirmation code",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Account sign in with Ecom SaaS",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = "OTP sent to this mobile number. $mobile",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = otpnumber1,
                onValueChange = {
                    if (it.length <= 1) {
                        otpnumber1 = it
                        if (it.isEmpty()) {
                            focusRequester1.requestFocus()
                        } else {
                            focusRequester2.requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
                    .focusRequester(focusRequester1),


                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp  // Adjust font size as needed
                ),
            )

            OutlinedTextField(
                value = otpnumber2,
                onValueChange = {
                    if (it.length <= 1) {
                        otpnumber2 = it
                        if (it.isEmpty()) {
                            focusRequester1.requestFocus()
                        } else {
                            focusRequester3.requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
                    .focusRequester(focusRequester2),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp  // Adjust font size as needed
                ),
            )

            OutlinedTextField(
                value = otpnumber3,
                onValueChange = {
                    if (it.length <= 1) {
                        otpnumber3 = it
                        if (it.isEmpty()) {
                            focusRequester2.requestFocus()
                        } else {
                            focusRequester4.requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
                    .focusRequester(focusRequester3),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp  // Adjust font size as needed
                ),
            )
//5456464
            OutlinedTextField(
                value = otpnumber4,
                onValueChange = {
                    if (it.length <= 1) {
                        otpnumber4 = it
                        if (it.isEmpty()) {
                            focusRequester3.requestFocus()
                        } else {
                            focusRequester5.requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
                    .focusRequester(focusRequester4),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp  // Adjust font size as needed
                ),
            )

            OutlinedTextField(
                value = otpnumber5,
                onValueChange = {
                    if (it.length <= 1) {
                        otpnumber5 = it
                        if (it.isEmpty()) {
                            focusRequester4.requestFocus()
                        } else {
                            focusRequester6.requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
                    .focusRequester(focusRequester5),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp  // Adjust font size as needed
                ),
            )

            OutlinedTextField(
                value = otpnumber6,
                onValueChange = {
                    if (it.length <= 1) {
                        otpnumber6 = it
                        if (it.isEmpty()) {
                            focusRequester5.requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
                    .focusRequester(focusRequester6),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp  // Adjust font size as needed
                ),

                )
        }
        Text(
            text = "Resend Code",
            fontSize = 14.sp,
            color = colorPrimary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        if (showhideotpreq)
            Text(
                text = otpreqerror,
                style = MaterialTheme.typography.subtitle1,
                color = bgred,
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 20.dp
                    )

            )

        if (showhidespinner)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }

        val ctx = LocalContext.current
        Button(
            onClick = {
                showhidespinner = true

                if (otpnumber1 + otpnumber2 + otpnumber3 + otpnumber4 + otpnumber5 + otpnumber6 == "") {
                    showhideotpreq = true
                    showhidespinner = false
                } else if (screenName == "loginscreen") {
                    val user = User(username="", name = "", email = "", password = "", mobile = mobile.toString(), otp = otpnumber1 + otpnumber2 + otpnumber3 + otpnumber4 + otpnumber5 + otpnumber6)
                    RetrofitInstance.api.verifyotp(user).enqueue(object : Callback<UserAuthResp> {
                        @SuppressLint("LongLogTag")
                        override fun onResponse(call: Call<UserAuthResp>, response: Response<UserAuthResp>) {
                            Log.i("User created:", response.body().toString())
                            if (response.code() == 200) {
                                showhidespinner = false
                                val mResponseObj = response.body().toString()
                                var mUserAuthResp = response.body()!!
                                Log.i("mUserAuthResp.code:", mUserAuthResp.code.toString())
                                Log.i("mUserAuthResp.message:", mUserAuthResp.message.toString())
                                Log.i("mUserAuthResp.data.token:", mUserAuthResp.data.token.toString())
                                Log.i("mUserAuthResp.data.payment_status:", mUserAuthResp.data.payment_status.toString())
                                PrefHelper().setStringToShared(SharedPrefKey.accessToken, mUserAuthResp.data.token.toString())
                                PrefHelper().setStringToShared(SharedPrefKey.paymentStatus, mUserAuthResp.data.payment_status.toString())
                                Toast.makeText(ctx, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
                                if (mUserAuthResp.data.payment_status == "" || mUserAuthResp.data.payment_status == "PENDING") {
                                    navController.popBackStack()
                                    navController.navigate(Screen.PaymentScreen.route)
                                } else {
                                    navController.popBackStack()
                                    navController.navigate(Screen.HomeScreen.route)
                                }
                            } else {
                                Toast.makeText(ctx, "OTP Verification failed", Toast.LENGTH_SHORT).show()
                                println("Error: ${response.code()}")
                                showhidespinner = false
                            }
                        }

                        override fun onFailure(call: Call<UserAuthResp>, t: Throwable) {
                            println("Network error: ${t.message}")
                            showhidespinner = false
                        }
                    })
                } else if (screenName == "registerscreen") {
                    val user = User(username="", name = "", email = "", password = "", mobile = mobile.toString(), otp = otpnumber1 + otpnumber2 + otpnumber3 + otpnumber4 + otpnumber5 + otpnumber6)
                    RetrofitInstance.api.signupsubmitotp(user).enqueue(object : Callback<RespData> {
                        override fun onResponse(call: Call<RespData>, response: Response<RespData>) {
                            if (response.code() == 200) {
                                navController.popBackStack()
                                navController.navigate(Screen.AddressDetails.createRoute(mobile.toString()))
                                Log.i("User created:", response.body().toString())
                                Toast.makeText(ctx, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()

                            } else {
                                Toast.makeText(ctx, "OTP Verification failed", Toast.LENGTH_SHORT).show()
                                println("Error: ${response.code()}")
                                println("Error: ${response.errorBody()}")
                                var mRespData = response.errorBody()!!
                                Log.i("User created:", mRespData.toString())
                            }
                        }

                        override fun onFailure(call: Call<RespData>, t: Throwable) {
                            println("Network error: ${t.message}")
                        }
                    })
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 30.dp,
                    bottom = 34.dp
                ),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
        ) {
            Text(
                text = "Continue",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun OtpScreenPreview() = OtpScreen(NavController(LocalContext.current), screenName = null, mobile = null)

@Preview
@Composable
fun HeaderOtpScreen() {
    Image(
        painter = painterResource(id = R.drawable.login_bg),
        contentDescription = "login_bg",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier.padding(bottom = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.flower_logo),
            contentDescription = "login_bg",
            modifier = Modifier.wrapContentWidth()
        )

        Text(
            text = "Ecom SaaS",
            color = white,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 2.sp
        )
    }
}
