package com.vilvanetworks.ayyawinstudyidp.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*

@Composable
fun EducationalDetail(navController: NavController) {
    val firaSansFamily = FontFamily(
        Font(R.font.dmsansregular, FontWeight.Normal),
        Font(R.font.dmsansmedium, FontWeight.Medium),
        Font(R.font.dmsansbold, FontWeight.Bold),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(280.dp)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                HeaderEducationalDetail()
            }

            Surface(
                color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
                    .constrainAs(loginformref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
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
                                    append("Address")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        dark_gray,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("details.")
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
                    var phonenumbererror by remember { mutableStateOf("Please enter valid phone number.") }
                    var address1 by remember { mutableStateOf("") }
                    var address2 by remember { mutableStateOf("") }
                    var city by remember { mutableStateOf("") }
                    var district by remember { mutableStateOf("") }
                    var state by remember { mutableStateOf("Please enter valid phone number.") }
                    var postalcode by remember { mutableStateOf("") }

                    TextField(

                        value = address1,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Face,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(PaddingValues(top = 20.dp))
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "address1") }, // Change label
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            address1 = it
                        }
                    )
                    TextField(
                        value = address1,
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
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = { Text(text = "address2") },
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            address1 = it
                        }
                    )
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

                    TextField(

                        value = address2,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(PaddingValues(top = 20.dp))
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "") }, // Change label
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            address2 = it
                        }
                    )
                    TextField(

                        value = city,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(PaddingValues(top = 20.dp))
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "city") }, // Change label
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            city = it
                        }
                    )

                    TextField(

                        value = district,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(PaddingValues(top = 20.dp))
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "district") }, // Change label
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            district = it
                        }
                    )


                    TextField(

                        value = state,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(PaddingValues(top = 20.dp))
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "state") }, // Change label
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            state = it
                        }
                    )

                    TextField(

                        value = postalcode,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(PaddingValues(top = 20.dp))
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "state") }, // Change label
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            postalcode = it
                        }
                    )






                    Button(
                        onClick = {
//                            navController.popBackStack()
//
                            navController.navigate(Screen.SignUp.route)
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
                            text = "Next",
                            color = white,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }



                }

            }

        }
    }
}

@Preview
@Composable
fun EducationalDetail() = EducationalDetail(NavController(LocalContext.current))


@Preview
@Composable
fun HeaderEducationalDetail() {
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
