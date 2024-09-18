package com.vilvanetworks.ayyawinstudyidp.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.objectInterface.*
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavController) {
    val firaSansFamily = FontFamily(
        Font(R.font.dmsansregular, FontWeight.Normal),
        Font(R.font.dmsansmedium, FontWeight.Medium),
        Font(R.font.dmsansbold, FontWeight.Bold),
    )


//    PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
//        ?.let { Log.d("payment", it) }
//    PrefHelper().clearAll()

    Box(

        modifier = Modifier
            .background(color = ghost_white)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = ghost_white)
                    .height(280.dp)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Header()
            }

            Surface(
                color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp)
                    .constrainAs(loginformref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.background(color = white, shape = RoundedCornerShape(40.dp).copy(
                        bottomStart = ZeroCornerSize,
                        bottomEnd = ZeroCornerSize
                    ))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        colorPrimary,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Log in ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        dark_gray,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("to your account.")
                                }

                            },
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                    }


                    Spacer(modifier = Modifier.padding(10.dp))

//                    Text(
//                        text = "User ID",
//                        style = MaterialTheme.typography.subtitle1,
//                        color = dark_gray,
//                        modifier = Modifier.padding(
//                            top = 10.dp,
//                            bottom = 20.dp
//                        )
//                    )

                    var phonenumber by remember { mutableStateOf("Meesakar") }
                    var phonenumbererror by remember { mutableStateOf("Please enter valid user id.") }
                    var showhidephonenumber by remember { mutableStateOf(false) }
                    var showhidepassword by remember { mutableStateOf(false) }
                    var otpreqerror by remember { mutableStateOf("Network failed, please try again later.") }
                    var regmoberror by remember { mutableStateOf("Invalid account, please do signup.") }
                    var showhideregmob by remember { mutableStateOf(false) }
                    var showhideotpreq by remember { mutableStateOf(false) }
                    var showhidespinner by remember { mutableStateOf(false) }
                    var password by remember { mutableStateOf("messakar@123") }
                    var passworderror by remember { mutableStateOf("Please enter valid password.") }

                    TextField(
                        value = phonenumber,

                        leadingIcon = {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                    Canvas(
                                        modifier = Modifier
                                            .height(24.dp)
                                            .padding(start = 10.dp)
                                    ) {
                                        drawLine(
                                            color = light_gray,
                                            start = Offset(0f, 0f),
                                            end = Offset(0f, size.height),
                                            strokeWidth = 2.0F
                                        )
                                    }
                                }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            textColor = black
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text(text = "User ID") },
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            phonenumber = it

                        }
                    )
                    if(showhidephonenumber)
                    Text(
                        text = phonenumbererror,
                        style = MaterialTheme.typography.subtitle1,
                        color = bgred,

                        modifier = Modifier
                            .padding(
                                top = 10.dp,
                                bottom = 20.dp
                            )

                    )

                    if(showhideotpreq)
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


                    if(showhideregmob)
                        Text(
                            text = regmoberror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 20.dp
                                )

                        )


//                    Text(
//                        text = "Password",
//                        style = MaterialTheme.typography.subtitle1,
//                        color = dark_gray,
//                        modifier = Modifier.padding(
//                            top = 10.dp,
//                            bottom = 20.dp
//                        )
//                    )


                    TextField(
                        value = password,
                        leadingIcon = {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                    Canvas(
                                        modifier = Modifier
                                            .height(24.dp)
                                            .padding(start = 10.dp)
                                    ) {
                                        drawLine(
                                            color = light_gray,
                                            start = Offset(0f, 0f),
                                            end = Offset(0f, size.height),
                                            strokeWidth = 2.0F
                                        )
                                    }
                                }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Password") },
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            password = it
                        }
                    )

                    if(showhidepassword)
                        Text(
                            text = passworderror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,

                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 20.dp
                                )

                        )
                    val ctx = LocalContext.current
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.End
//                    ) {
//                        Text(
//                            text = "Resend OTP",
//                            style = MaterialTheme.typography.subtitle2,
//                            color = colorPrimary,
//                            textAlign = TextAlign.End,
//                            modifier = Modifier
//                                .padding(top = 10.dp)
//                                .clickable {
//
//
//
//                                }
//                        )
//                    }


                    if(showhidespinner)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IndeterminateCircularIndicator()
                    }





                    Button(
                        onClick = {

                            try{


//                            navController.popBackStack()
                                showhidephonenumber = false;
                                showhideotpreq = false;
                                showhideregmob = false;
                                showhidespinner = true;
                                if(password === ""){
                                    showhidepassword = true;
                                } else {
                                    showhidepassword = false;
                                }
                                if(phonenumber === ""){
                                    showhidephonenumber = true;
                                    showhidespinner = false;
                                } else {
                                    val gson = Gson()
                                    val user = User(username = phonenumber, name = "", email = "", password = password, mobile = phonenumber, otp = "")
                                    RetrofitInstance.api.signin(user).enqueue(object : Callback<ResponseBody> {

                                        @SuppressLint("LongLogTag")
                                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                            var jsonparsesucc: String? = null
                                            var jsonparseerr: String? = null

                                            // Read the body and errorBody only once
                                            val bodyString = response.body()?.string()
                                            val errorBodyString = response.errorBody()?.string()

                                            Log.i("Login", bodyString ?: "No response body")

                                            // Assign the response body or error body to the respective variables
                                            jsonparsesucc = bodyString
                                            jsonparseerr = errorBodyString

                                            val gson = Gson()
                                            var errorResponseerr: ErrorResponse? = null
                                            var errorResponsesucc: LoginResponse? = null

                                            // Parse JSON strings to corresponding objects
                                            if (jsonparseerr != null) {
                                                errorResponseerr = gson.fromJson(jsonparseerr, ErrorResponse::class.java)
                                            }

                                            if (jsonparsesucc != null) {
                                                errorResponsesucc = gson.fromJson(jsonparsesucc, LoginResponse::class.java)
                                            }

                                            // Flags to check which response was received
                                            var successDataFlag = false
                                            var errorDataFlag = false

                                            // Print the parsed object for successful response
                                            if (errorResponsesucc != null) {
                                                Log.d("Login:", "Code: ${errorResponsesucc.statuscode}, Message: ${errorResponsesucc.message}")
                                                successDataFlag = true
                                            }

                                            // Print the parsed object for error response
                                            if (errorResponseerr != null) {
                                                Log.d("Login:", "Code: ${errorResponseerr.statuscode}, Message: ${errorResponseerr.message}")
                                                errorDataFlag = true
                                            }

                                            // Handle the success response case
                                            if (response.isSuccessful && errorResponsesucc != null) {
                                                Log.i("User created:", errorResponsesucc.message)
                                                Log.i("Login", errorResponsesucc.token)
                                                Toast.makeText(ctx, "User Logged In Successfully", Toast.LENGTH_SHORT).show()

                                                // Store the access token in shared preferences
                                                PrefHelper().setStringToShared(SharedPrefKey.accessToken, errorResponsesucc.token)
                                                PrefHelper().setIntToShared(SharedPrefKey.storeid, errorResponsesucc.storeid)
                                                val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
                                                if (accessToken != null) {
                                                    Log.d("Login", accessToken)
                                                }
                                                // Navigate to HomeScreen
                                                navController.popBackStack()
                                                navController.navigate(Screen.HomeScreen.route)

                                            } else {
                                                // Handle error cases or failure
                                                Log.e("Error:", response.toString())
                                                Log.e("Error message:", response.message())
                                                Toast.makeText(ctx, "Login failed", Toast.LENGTH_SHORT).show()

                                                // If errorResponseerr exists, handle the error message
                                                errorResponseerr?.let {
                                                    Log.e("Error:", "Code: ${it.statuscode}, Message: ${it.message}")
                                                }
                                            }

                                            showhidespinner = false
                                        }

                                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                            showhideotpreq = true
                                            showhidespinner = false
                                            Log.e("Network error:", t.message ?: "Unknown error")
                                            Toast.makeText(ctx, "Network Error", Toast.LENGTH_SHORT).show()
                                        }
                                    })

//                                navController.navigate(Screen.Otpscreen.createRoute("loginscreen"))
                                }

                            } catch (e: Exception) {
                                // on below line we are handling exception.
                                Log.d("user", e.printStackTrace().toString())
                                e.printStackTrace()
                                Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                            }

                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 30.dp,
                                bottom = 34.dp
                            )
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Login",
                            color = white,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }

                    //EXTENDED FAB WITHOUT ICON


//                    FloatingActionButton(
//                        onClick = {
//
//                        },
//                        backgroundColor = MaterialTheme.colors.primary,
//                        contentColor = Color.White,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(
//                                top = 30.dp,
//                                bottom = 34.dp
//                            ).constrainAs(loginformref) {
//                            end.linkTo(parent.end, margin = 16.dp)
//                            bottom.linkTo(parent.bottom, margin = 16.dp)
//                            }.align(Alignment.CenterHorizontally),
//                            shape = RoundedCornerShape(16.dp)
//                    ) {
//                        Text(
//                            text = "Login",
//                            color = white,
//                            style = MaterialTheme.typography.button,
//                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
//                        )
//                    }


//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = buildAnnotatedString {
//                                append("Don't have an account? Signup")
//                                addStyle(
//                                    SpanStyle(color = colorPrimary),
//                                    23,
//                                    this.length
//                                )
//                            },
//
//                            style = MaterialTheme.typography.subtitle1,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.clickable {
//                                navController.navigate(Screen.SignUp.route)
//                            }
//                        )
//                    }

                }

            }

        }
    }
}



@Composable
fun IndeterminateCircularIndicator() {

    CircularProgressIndicator(
        modifier = Modifier.width(32.dp),
        color = MaterialTheme.colors.secondary,
    )
}

@Preview
@Composable
fun LoginScreenPreview() = LoginScreen(NavController(LocalContext.current))


@Preview
@Composable
fun Header() {
    Image(
        painter = painterResource(id = R.drawable.login_bg),
        contentDescription = "login_bg",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier.padding(bottom = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "login_bg",
            modifier = Modifier.wrapContentWidth()
        )

        Text(
            text = "Ecom SaaS",
            color = white,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
