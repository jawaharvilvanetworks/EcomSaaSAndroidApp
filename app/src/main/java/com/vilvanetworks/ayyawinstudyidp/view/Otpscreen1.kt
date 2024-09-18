package com.vilvanetworks.ayyawinstudyidp.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.input.ImeAction
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
import androidx.navigation.compose.rememberNavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*

@Composable
fun OtpScreen1(navController: NavController) {
    val firaSansFamily = FontFamily(
        Font(R.font.dmsansregular, FontWeight.Normal),
        Font(R.font.dmsansmedium, FontWeight.Medium),
        Font(R.font.dmsansbold, FontWeight.Bold),
    )


    var otpnumber1 by remember { mutableStateOf("")}
    var otpnumber2 by remember { mutableStateOf("")}
    var otpnumber3 by remember { mutableStateOf("")}
    var otpnumber4 by remember { mutableStateOf("")}
    var otpnumber5 by remember { mutableStateOf("")}
    var otpnumber6 by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = otpnumber1,
                onValueChange = {
                    otpnumber1 = it.substring(0,1)
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                singleLine = true,
            )

            OutlinedTextField(
                value = otpnumber2,
                onValueChange = {
                    otpnumber2 = it.substring(0,1)
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                singleLine = true,
            )

            OutlinedTextField(
                value = otpnumber3,
                onValueChange = {
                    otpnumber3 = it.substring(0,1)
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                singleLine = true,
            )

            OutlinedTextField(
                value = otpnumber4,
                onValueChange = {
                    otpnumber4 = it.substring(0,1)
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                singleLine = true,
            )

            OutlinedTextField(
                value = otpnumber5,
                onValueChange = {
                    otpnumber5 = it.substring(0,1)
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                singleLine = true,
            )

            OutlinedTextField(
                value = otpnumber6,
                onValueChange = {
                    otpnumber6 = it.substring(0,1)
                },
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                singleLine = true,
            )

        }
        Text(
            text = "Resend Code",
            fontSize = 14.sp,
            color = Color(0xFF006FFD),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Button(
            onClick = {
                navController.navigate(Screen.AddressDetails.route);
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF006FFD))
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
fun OtpScreen1Preview() = OtpScreen1(NavController(LocalContext.current))


@Preview
@Composable
fun HeaderOtpScreen1() {
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
