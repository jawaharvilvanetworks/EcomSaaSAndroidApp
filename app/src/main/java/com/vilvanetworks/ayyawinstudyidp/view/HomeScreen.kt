package com.vilvanetworks.ayyawinstudyidp.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import com.google.gson.Gson
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.objectInterface.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public var colorhighlightdashboard = mutableStateOf(true)
public var colorhighlighttasks = mutableStateOf(false)
public var colorhighlightmyaccount = mutableStateOf(false)

@Composable
public fun HomeScreen(navController: NavController) {
    val pageCount by remember { mutableStateOf(0) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ghost_white)
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(0.dp)
                    .background(color = ghost_white)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
//                HeaderHome()
                pageInit(navController)
            }

            Surface(
                color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp)
                    .constrainAs(loginformref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = white,
                        shape = RoundedCornerShape(40.dp).copy(
                            bottomStart = ZeroCornerSize,
                            bottomEnd = ZeroCornerSize
                        ),
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {


                    PageIndicator(pageCount)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (colorhighlightdashboard.value) Color(0xFF006FFD) else Color.White,
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(10.dp)


                        ) {
                            Text(
                                text = "Dashboard",
                                style = TextStyle(fontWeight = FontWeight.Bold, color = if (colorhighlightdashboard.value) white else black,),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .clickable {
                                        colorhighlightdashboard.value = true;
                                        colorhighlighttasks.value = false;
                                        colorhighlightmyaccount.value = false;
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (colorhighlighttasks.value) Color(0xFF006FFD) else Color.White,
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(10.dp)


                        ) {
                            Text(
                                text = "Orders",
                                style = TextStyle(fontWeight = FontWeight.Bold, color = if (colorhighlighttasks.value) white else black,),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .clickable {
                                        colorhighlightdashboard.value = false;
                                        colorhighlighttasks.value = true;
                                        colorhighlightmyaccount.value = false;
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (colorhighlightmyaccount.value) Color(0xFF006FFD) else Color.White,
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "My Account",
                                style = TextStyle(fontWeight = FontWeight.Bold, color =  if (colorhighlightmyaccount.value) white else black,),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .clickable {
                                        colorhighlightdashboard.value = false;
                                        colorhighlighttasks.value = false;
                                        colorhighlightmyaccount.value = true;
                                    }
                            )
                        }
                    }



                    if(colorhighlightdashboard.value){
//                        Spacer(modifier = Modifier.padding(10.dp))
//                        SlidingBannerView()
//                        PopularItemSection(navController)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            DashboardCard(title = "Orders Total Count", value = orderstotalcount)
                            DashboardCard(title = "Orders Success Count", value = orderssuccesscount)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            DashboardCard(title = "Total Customers", value = customerstotalcount)
                            DashboardCard(title = "Active Customers", value = activecustomerstotalcount)
                        }
                    }

                    if(colorhighlighttasks.value){
                        Spacer(modifier = Modifier.padding(10.dp))
                        TaskScreen(navController)
                    }

                    if(colorhighlightmyaccount.value){
                        Spacer(modifier = Modifier.padding(10.dp))
                        MyAccount(navController)
                    }

                }

            }

        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() = HomeScreen(NavController(LocalContext.current))

@Preview
@Composable
fun HeaderHome() {
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

@Composable
fun DashboardCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(140.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 18.sp)
            Text(text = value, fontSize = 24.sp, color = Color.Blue)
        }
    }
}

@Composable
fun DashboardList(items: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (item in items) {
            Text(text = item, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}

var accountdata : AccountData? = AccountData()
var taskdata : List<TaskData>? = emptyList()
val ctx = LocalContext
var dbdashboardResponse: DashboardResponse? = null
var orderstotalcount by mutableStateOf("0")
    private set
var orderssuccesscount by mutableStateOf("0")
var customerstotalcount by mutableStateOf("0")
var activecustomerstotalcount by mutableStateOf("0")
// Function to update the state variable
fun updateDashboardData(dashboardResponse: DashboardResponse) {
    orderstotalcount = dashboardResponse.data.orders.total_count.toString()
    orderssuccesscount = dashboardResponse.data.orders.success_count.toString()
    customerstotalcount = dashboardResponse.data.customers.total.toString()
    activecustomerstotalcount = dashboardResponse.data.customers.active.toString()
}

fun pageInit(navController: NavController) {
    val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
    val storeId = PrefHelper().getIntFromShared(SharedPrefKey.storeid)

    if (!accessToken.isNullOrEmpty()) {
        Log.d("accessToken", accessToken)
    }
    if (accessToken.isNullOrEmpty()) {
        navController.navigate(Screen.LoginScreen.route)
        return;
    } else {



        try{

            RetrofitInstance.api.getDashboard(accessToken, storeId.toString()).enqueue(object : Callback<DashboardResponse> {
                override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                    if (response.isSuccessful) {

                        val dashboardResponse = response.body()
                        if (dashboardResponse != null) {
                            // Handle successful response

                            updateDashboardData(dashboardResponse)
                            Log.d("Dashboard", "Message: ${dashboardResponse.message}")
                            Log.d("Dashboard", "Message: ${dashboardResponse}")
                        } else {
                            Log.e("Dashboard", "Response body is null")
                        }
                    } else {
                        Log.e("Dashboard", "Failed to fetch dashboard: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                    Log.e("Dashboard", "Network error: ${t.message}")
                }
            })


        } catch (e: Exception) {

        }


    }

}

fun getAccountDataHomeScreen(): AccountData {
    return accountdata!!;
}

fun getTasksDataHomeScreen(): List<TaskData> {
    return taskdata!!;
}


@Composable
fun PageIndicator(pageCount: Int) {

}

@Preview
@Composable
fun PageIndicatorPreview() = PageIndicator(2)



@Composable
fun CategoryButton(
    icon: Painter,
    backgroundColor: Color,
) {
    Box(
        Modifier
            .size(72.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {
        Image(
            painter = icon, contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun CategoryButtonPreview() = CategoryButton(
    painterResource(id = R.drawable.ic_wedding_arch),
    Color(0xffFFFFFF),
)

@Composable
fun PopularItemSection(navController: NavController) {
    Spacer(modifier = Modifier.padding(10.dp))
    PopularItems(navController)
}

@Preview
@Composable
fun PopularItemSectionPreview() = PopularItemSection(NavController(LocalContext.current))

@Preview
@Composable
fun SlidingBannerView() {
    Image(
        painter = painterResource(id = R.drawable.ic_sale_banner),
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PopularItems(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Heading
        Text(
            text = "Task Name Here",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Sub Heading
        Text(
            text = "Sub Heading Here",
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Paragraph
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Button

        Button(

            onClick = {
                colorhighlightdashboard.value = false;
                colorhighlighttasks.value = true;
                colorhighlightmyaccount.value = false;

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF006FFD),
                contentColor = Color.White
            )
        ) {
            Text(text = "Continue Task")
        }

    }

}

@Preview
@Composable
fun PopularItemsPreview() = PopularItems(NavController(LocalContext.current))
//@Preview
//@Composable
//fun TaskScreenPreview() {
//    TaskScreen()
//}
//
//@Preview
//@Composable
//fun MyAccountview() {
//    MyAccount()
//}
//@Preview
//@Composable
//fun OtpScreenview() {
//    OtpScreen()
//}
//@Preview
//@Composable
//fun SignUpview() {
//    SignUp()
//}