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
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.objectInterface.ErrorResponse
import com.vilvanetworks.ayyawinstudyidp.objectInterface.SuccessResponse
import com.vilvanetworks.ayyawinstudyidp.objectInterface.User
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("SuspiciousIndentation")
@Composable
fun SignUp(navController: NavController) {
    val firaSansFamily = FontFamily(
        Font(R.font.dmsansregular, FontWeight.Normal),
        Font(R.font.dmsansmedium, FontWeight.Medium),
        Font(R.font.dmsansbold, FontWeight.Bold),
)


    Box(
        modifier = Modifier
            .background(color = ghost_white)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = ghost_white)
                    .height(280.dp)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                HeaderSignup()
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
                                    append("Create")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        dark_gray,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append(" your account.")
                                }

                            },
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                    }


                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Phone number",
                        style = MaterialTheme.typography.subtitle1,
                        color = dark_gray,
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 20.dp
                        )
                    )

                    var phonenumber by remember { mutableStateOf("")}
                    var type by remember { mutableStateOf("") }
                    var email by remember { mutableStateOf("") }
                    var name by remember { mutableStateOf("") }
                    var phonenumbererror by remember { mutableStateOf("Please enter valid phone number.") }
                    var existingphonenumbererror by remember { mutableStateOf("Mobile number already registered.") }
                    var showhidephonenumber by remember { mutableStateOf(false) }
                    var showhideexistingnumber by remember { mutableStateOf(false) }
                    var showhidespinner by remember { mutableStateOf(false) }
//                    TextField(
//
//                        value = name,
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.Face,
//                                contentDescription = null,
//                                tint = colorPrimary
//                            )
//                        },
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = white,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            disabledIndicatorColor = Color.Transparent
//                        ),
//                        modifier = Modifier
//                            .padding(PaddingValues(top = 20.dp))
//                            .fillMaxWidth(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        label = { Text(text = "Name") }, // Change label
//                        shape = RoundedCornerShape(8.dp),
//                        onValueChange = {
//                            name = it
//                        }
//                    )
                    TextField(
                        value = phonenumber,
                        leadingIcon = {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = { Text(text = "Phone number") },
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

                    if(showhideexistingnumber)
                    Text(
                        text = existingphonenumbererror,
                        style = MaterialTheme.typography.subtitle1,
                        color = bgred,
                        modifier = Modifier
                            .padding(
                                top = 10.dp,
                                bottom = 20.dp
                            )

                    )
//                    Text(
//                        text = phonenumbererror,
//                        style = MaterialTheme.typography.subtitle1,
//                        color = bgred,
//                        modifier = Modifier
//                            .padding(
//                                top = 10.dp,
//                                bottom = 20.dp
//                            )
//
//                    )

//                    TextField(
//
//                        value = email,
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.Email,
//                                contentDescription = null,
//                                tint = colorPrimary
//                            )
//                        },
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = white,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            disabledIndicatorColor = Color.Transparent
//                        ),
//                        modifier = Modifier
//                            .padding(PaddingValues(top = 20.dp))
//                            .fillMaxWidth(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        label = { Text(text = "Email address") }, // Change label
//                        shape = RoundedCornerShape(8.dp),
//                        onValueChange = {
//                            email = it
//                        }
//                    )
//                    TextField(
//
//                        value = type,
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.PlayArrow,
//                                contentDescription = null,
//                                tint = colorPrimary
//                            )
//                        },
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = white,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            disabledIndicatorColor = Color.Transparent
//                        ),
//                        modifier = Modifier
//                            .padding(PaddingValues(top = 20.dp))
//                            .fillMaxWidth(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        label = { Text(text = "Type") }, // Change label
//                        shape = RoundedCornerShape(8.dp),
//                        onValueChange = {
//                            type = it
//                        }
//                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                    if(showhidespinner)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IndeterminateCircularIndicator()
                        }


                    val ctx = LocalContext.current


                    Button(
                        onClick = {

                            try{
//                            navController.popBackStack()
                            showhideexistingnumber = false;
                            showhidephonenumber = false;
                            showhidespinner = true;
                            if(phonenumber === ""){
                                showhidephonenumber = true;
                                showhidespinner = false;
                            } else {

                                val user = User(username="", name = "", email = "", password = "", mobile = phonenumber, otp = "")
//                                Log.i("user", RetrofitInstance.api.signuprequestotp.toString())
                                RetrofitInstance.api.signuprequestotp(user).enqueue(object : Callback<ResponseBody> {
                                    @SuppressLint("LongLogTag")
                                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                                        var jsonparsesucc : String? =  null
                                        var jsonparseerr : String? =  null

                                        if(response.body() != null)
                                         jsonparsesucc = response.body()!!.string()

                                        if(response.errorBody() != null)
                                            jsonparseerr = response.errorBody()!!.string()

                                        val jsonStringsucc = """${jsonparsesucc}"""
                                        val jsonStringerr = """${jsonparseerr}"""

                                        val gson = Gson()
                                        var errorResponseerr: ErrorResponse? = null;
                                        var errorResponsesucc: SuccessResponse? = null;
                                        if(jsonStringerr != null)
                                         errorResponseerr = gson.fromJson(jsonStringerr, ErrorResponse::class.java)
                                        if(jsonStringsucc != null)
                                          errorResponsesucc = gson.fromJson(jsonStringsucc, SuccessResponse::class.java)

                                        var successDataFlag = false;
                                        var errorDataFlag = false;
                                        // Print the parsed object
                                        if(errorResponsesucc != null){
                                            println("Code: ${errorResponsesucc.code}, Message: ${errorResponsesucc.message}")
                                            Log.d("mresponse:","Code: ${errorResponsesucc.code}, Message: ${errorResponsesucc.message}")
                                            successDataFlag = true;
                                        }

                                        if(errorResponseerr != null){
                                            println("Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}")
                                            Log.d("mresponse:","Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}")
                                            errorDataFlag = true;
                                        }

                                        if (successDataFlag) {
                                            showhidespinner = false;
                                            Toast.makeText(ctx,"OTP Sent Successfully", Toast.LENGTH_SHORT).show()
                                            navController.navigate(Screen.Otpscreen.createRoute("registerscreen", phonenumber))
//
                                        } else if(errorDataFlag) {
                                            showhideexistingnumber = true;
                                            showhidespinner = false;
                                            if (errorResponseerr != null) {
                                                existingphonenumbererror = errorResponseerr.message
                                            }
//
                                        }
                                    }

                                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                        println("Network error: ${t.message}");
                                        showhidespinner = false;
                                    }
                                })
//                                navController.navigate(Screen.Otpscreen.createRoute("loginscreen"))
                            }

                            } catch (e: Exception) {
                                // on below line we are handling exception.
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
                            text = "Send OTP",
                            color = white,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("Already have an account? Sign in")
                                addStyle(
                                    SpanStyle(color = colorPrimary),
                                    24,
                                    this.length
                                )
                            },

                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.LoginScreen.route)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.padding(100.dp))

                }

            }

        }
    }
}

@Preview
@Composable
fun SignUp() = SignUp(NavController(LocalContext.current))


@Preview
@Composable
fun HeaderSignup() {
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
