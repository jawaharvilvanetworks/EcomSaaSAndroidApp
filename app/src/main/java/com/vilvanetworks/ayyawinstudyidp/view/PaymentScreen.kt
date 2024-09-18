package com.vilvanetworks.ayyawinstudyidp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import com.vilvanetworks.ayyawinstudyidp.objectInterface.PaymentAuthResp
import com.vilvanetworks.ayyawinstudyidp.objectInterface.UserPayment
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.MainActivity
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen

import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import java.text.SimpleDateFormat
import java.util.*

val Purple200 = Color(0xFF0F9D58)
val Purple500 = Color(0xFF0F9D58)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// on below line we are adding different colors.
val greenColor = Color(0xFF0F9D58)
val showDialog = mutableStateOf(false)
public var PaymentScreenpaymentstatus = mutableStateOf(0)





    @Composable
    fun PaymentScreen(navController: NavController) {

        if(PaymentScreenpaymentstatus.value === 0){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Unlock Your Full Potential! 💳",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Complete your payment to gain access to all premium features and resources. Enjoy unlimited access and enhance your student experience!",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(color = Color(0xFFEAF2FF), shape = RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Plan Name",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.size(16.dp, 4.dp))
                            Text(
                                text = "Description or discount",
                                fontSize = 14.sp,
                                color = Color(0xFF006FFD)
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(

                                fontWeight = FontWeight.Bold,
                                text = "₹ 1599.80",
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.End)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Duration",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(bottom = 20.dp)
                        .background(color = Color(0xFFEAF2FF), shape = RoundedCornerShape(20.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "You'll get:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        ItemRow("Plant Seeds")
//                ItemRow("Product Name 1")
//                ItemRow("Product Name 2")
//                ItemRow("Product Name 3")
//                ItemRow("Product Name 4")
//                ItemRow("Product Name 5")
                    }
                }
                val ctx = LocalContext.current
                val activity = (LocalContext.current as? Activity)
                val mainActivity = (LocalContext.current as? MainActivity)

                Button(
                    onClick = {
                        val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
                        val user = UserPayment(amount = "")

//                        PaymentScreenpaymentstatus.value = 1


                        RetrofitInstance.api.haodapaysubmit(token = "Bearer ${accessToken}", user).enqueue(object : Callback<PaymentAuthResp> {
                            @SuppressLint("LongLogTag")
                            override fun onResponse(call: Call<PaymentAuthResp>, response: Response<PaymentAuthResp>) {

                                if (response.code() == 200) {
                                    var mPaymentAuthResp =  response.body()!!
                                    Log.i("mPaymentAuthResp.code:", mPaymentAuthResp.code.toString())
                                    Log.i("mPaymentAuthResp.message:", mPaymentAuthResp.message.toString())
                                    Log.i("mPaymentAuthResp.data.intent_link:", mPaymentAuthResp.data.intent_link)
                                    var _parseUpiUrl = parseUpiUrl(mPaymentAuthResp.data.intent_link)

                                    try {
                                        makePayment(
                                            _parseUpiUrl.get("am")!!,
                                            _parseUpiUrl.get("pa")!!,
                                            _parseUpiUrl.get("pn")!!,
                                            _parseUpiUrl.get("tr")!!,
                                            _parseUpiUrl.get("tn")!!,
                                            ctx ,
                                            activity!!,
                                            mainActivity!!
                                        )
                                    } catch (e: Exception) {
                                        // on below line we are handling exception.
                                        e.printStackTrace()
                                        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                                    }



//                            makePayment(
//                                amount.value.text,
//                                upiId.value.text,
//                                name.value.text,
//                                description.value.text,
//                                transcId,
//                                ctx,
//                                activity!!,
//                                mainActivity
//                            )

//                            PrefHelper().setStringToShared(SharedPrefKey.accessToken, mUserAuthResp.data.token.toString())
//                            Toast.makeText(ctx,"OTP Verified Successfully", Toast.LENGTH_SHORT).show()
//                            navController.navigate(Screen.HomeScreen.route);
                                    Log.i("User created:", response.body().toString())
                                } else {
//                            Toast.makeText(ctx,"OTP Sending failed", Toast.LENGTH_SHORT).show()
//                            println("Error: ${response.code()}")
                                }
                            }



                            override fun onFailure(call: Call<PaymentAuthResp>, t: Throwable) {
                                println("Network error: ${t.message}")
                            }
                        })
////
//
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF006FFD))
                ) {
                    Text(
                        text = "Pay",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }
        } else if(PaymentScreenpaymentstatus.value === 1) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Green Tick",
                    tint = Color.Green,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "Thanks for your payment!",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Payment completed, Enjoy unlimited access and enhance your student experience!",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 16.dp)
                )


                val ctx = LocalContext.current
                val activity = (LocalContext.current as? Activity)
                val mainActivity = (LocalContext.current as? MainActivity)

                Button(
                    onClick = {

                        navController.navigate(Screen.HomeScreen.route)

                        },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF006FFD))
                ) {
                    Text(
                        text = "Show Dashboard",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }
        } else if(PaymentScreenpaymentstatus.value === 2) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Red Tick",
                    tint = Color.Red,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "Payment Failed",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Payment was not completed, Try again!",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 16.dp)
                )


                val ctx = LocalContext.current
                val activity = (LocalContext.current as? Activity)
                val mainActivity = (LocalContext.current as? MainActivity)

                Button(
                    onClick = {

                        PaymentScreenpaymentstatus.value = 0

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF006FFD))
                ) {
                    Text(
                        text = "Pay again",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }
        }

    }




fun parseUpiUrl(upiUrl: String): Map<String, String> {
    val url = upiUrl.removePrefix("upi://pay?")
    val params = url.split("&")
    val paramMap = mutableMapOf<String, String>()

    for (param in params) {
        val keyValue = param.split("=")
        if (keyValue.size == 2) {
            paramMap[keyValue[0]] = keyValue[1]
        }
    }

    return paramMap
}


    @Composable
    fun upiPayments(mainActivity: MainActivity) {
        val ctx = LocalContext.current
        val activity = (LocalContext.current as? Activity)

        val amount = remember {
            mutableStateOf(TextFieldValue())
        }
        val upiId = remember {
            mutableStateOf(TextFieldValue())
        }
        val name = remember {
            mutableStateOf(TextFieldValue())
        }
        val description = remember {
            mutableStateOf(TextFieldValue())
        }


        // on the below line we are creating a column.
        Column(
            // on below line we are adding a modifier to it
            // and setting max size, max height and max width
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth(),

            // on below line we are adding vertical
            // arrangement and horizontal alignment.
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            // on below line we are creating a text field for our email.
            TextField(
                // on below line we are specifying
                // value for our email text field.
                value = amount.value,

                // on below line we are adding on
                // value change for text field.
                onValueChange = { amount.value = it },

                // on below line we are adding place holder
                // as text as "Enter your email"
                placeholder = { Text(text = "Enter amount to be paid") },

                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                // on below line we are adding
                // single line to it.
                singleLine = true,

                )


            // on below line we are creating a text field for our email.
            TextField(
                // on below line we are specifying value
                // for our email text field.
                value = upiId.value,

                // on below line we are adding on value
                // change for text field.
                onValueChange = { upiId.value = it },

                // on below line we are adding place holder as
                // text as "Enter your email"
                placeholder = { Text(text = "Enter UPI Id") },

                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                // on below line we are adding single line to it.
                singleLine = true,
            )


            // on below line we are creating a text field for our email.
            TextField(
                // on below line we are specifying value
                // for our email text field.
                value = name.value,

                // on below line we are adding on value
                // change for text field.
                onValueChange = { name.value = it },

                // on below line we are adding place holder
                // as text as "Enter your email"
                placeholder = { Text(text = "Enter name") },

                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                // on below line we are adding
                // single line to it.
                singleLine = true,
            )


            // on below line we are creating a text field for our email.
            TextField(
                // on below line we are specifying value
                // for our email text field.
                value = description.value,

                // on below line we are adding on value change for text field.
                onValueChange = { description.value = it },

                // on below line we are adding place holder
                // as text as "Enter your email"
                placeholder = { Text(text = "Enter Description") },

                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                // on below line we are adding single line to it.
                singleLine = true,
            )


            Button(
                onClick = {
                    // on below line we are getting date and then we
                    // are setting this date as transaction id.
                    val c: Date = Calendar.getInstance().getTime()
                    val df = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault())
                    val transcId: String = df.format(c)

                    // on below line we are calling make
                    // payment method to make payment.


                    makePayment(
                        amount.value.text,
                        upiId.value.text,
                        name.value.text,
                        description.value.text,
                        transcId,
                        ctx,
                        activity!!,
                        mainActivity
                    )
                },
                // on below line we are adding modifier to our button.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // on below line we are adding text for our button
                Text(text = "Make Payment", modifier = Modifier.padding(8.dp))
            }
        }
    }

    // on below line we are creating
// a make payment method to make payment.
    private fun makePayment(
        amount: String,
        upi: String,
        name: String,
        desc: String,
        transcId: String, ctx: Context, activity: Activity, mainActivity: PaymentStatusListener
    ) {
        try {
            // START PAYMENT INITIALIZATION
            val easyUpiPayment = EasyUpiPayment(activity) {
                this.paymentApp = PaymentApp.ALL
                this.payeeVpa = upi
                this.payeeName = name
                this.transactionId = transcId
                this.transactionRefId = transcId
                this.payeeMerchantCode = transcId
                this.description = desc
                this.amount = amount
            }
            // END INITIALIZATION

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener(mainActivity)

            // Start payment / transaction
            easyUpiPayment.startPayment()
        } catch (e: Exception) {
            // on below line we are handling exception.
            e.printStackTrace()
            Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @Composable
    fun ItemRow(text: String) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "★",
                fontSize = 16.sp,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
