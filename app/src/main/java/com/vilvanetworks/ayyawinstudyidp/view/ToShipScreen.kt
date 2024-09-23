package com.vilvanetworks.ayyawinstudyidp.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.vilvanetworks.ayyawinstudyidp.ui.theme.ghost_white


import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.vilvanetworks.ayyawinstudyidp.objectInterface.*
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import kotlinx.coroutines.*


import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.graphics.Color
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen


import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Define the ShippingCompany data class
data class ShippingCompany(val id: Int, val name: String)

// Initialize the list of shipping companies
val shippingCompanies = listOf(
    ShippingCompany(1, "India Post"),
    ShippingCompany(2, "Xpressbees"),
    ShippingCompany(3, "TPC"),
    ShippingCompany(4, "ST"),
    ShippingCompany(5, "Ecom Express"),
    ShippingCompany(6, "Fedex"),
    ShippingCompany(7, "DTDC"),
    ShippingCompany(8, "Ekart"),
    ShippingCompany(9, "Delivery Air"),
    ShippingCompany(10, "Delivery Surface"),
    ShippingCompany(11, "Bluedart"),
    ShippingCompany(12, "Amazon Shipping"),
    ShippingCompany(13, "Smarter"),
    ShippingCompany(14, "DTDC Premium"),
    ShippingCompany(15, "Shadowfax"),
    ShippingCompany(16, "Kerry Indev")
)

@SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
@Composable
fun ToShipScreen(navController: NavController) {

    // Declare state inside the composable
    var expanded by remember { mutableStateOf(false) }
    var selectedCompany by remember { mutableStateOf(shippingCompanies[0].name) }
    var selectedCompanyId by remember { mutableStateOf(shippingCompanies[0].id) }
    // State to control the Snackbar visibility
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope() // To launch coroutines

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ghost_white)
            .padding(16.dp),  // Ensure enough padding
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Retrieve data from PrefHelper
        val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
        val storeId = PrefHelper().getIntFromShared(SharedPrefKey.storeid)
        val orderId = PrefHelper().getStringFromShared(SharedPrefKey.orderid)
        val barcodeNo = PrefHelper().getStringFromShared(SharedPrefKey.barcodeno)

//        TextField(
//            value = selectedCompany,
//            onValueChange = {},
//            label = { Text("Select Shipping Company") },
//            readOnly = true,  // Make it non-editable
//            modifier = Modifier
//                .fillMaxWidth()
//                .pointerInput(Unit) {
//                    detectTapGestures(onTap = {
//                        Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
//                        expanded = !expanded  // Toggle dropdown visibility
//                    })
//                },
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = "Dropdown Icon"
//                )
//            }
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//                    Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
//                    expanded = !expanded  // Toggle dropdown visibility
//                }
//        ) {
//            TextField(
//                value = selectedCompany,
//                onValueChange = {},
//                label = { Text("Select Shipping Company") },
//                readOnly = true,  // Make it non-editable
//                modifier = Modifier.fillMaxWidth(),
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.ArrowDropDown,
//                        contentDescription = "Dropdown Icon"
//                    )
//                }
//            )
//        }
        // Dropdown menu for selecting shipping companies
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
                .clickable { Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
                    expanded = !expanded },// Prevent clipping
        ) {
            Column {
                // Display the selected company in a TextField
//                TextField(
//                    value = selectedCompany,
//                    onValueChange = {},
//                    label = { Text("Select Shipping Company") },
//                    readOnly = true,  // Make it non-editable
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
//                            expanded = !expanded },  // Open/close dropdown on click
//                    trailingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.ArrowDropDown,
//                            contentDescription = "Dropdown Icon"
//                        )
//                    }
//                )


                Button(
                    onClick = {
                        expanded = !expanded
                        // Action to be performed when the Save button is clicked
                        // For example, navigate or save the selectedCompany to preferences
                        println("Selected shipping company: $selectedCompany")
                        Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,  // Set button background color to green
                        contentColor = Color.White      // Set the text/icon color to white
                    ),
                    modifier = Modifier.fillMaxWidth()  // Make the button fill the width
                ) {
//                    TextField(
//                        value = selectedCompany,
//                        onValueChange = {},
//                        label = { Text("Select Shipping Company") },
//                        readOnly = true,  // Make it non-editable
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable { Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
//                                expanded = !expanded },  // Open/close dropdown on click
//                        trailingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.ArrowDropDown,
//                                contentDescription = "Dropdown Icon"
//                            )
//                        }
//                    )

                    Text(text = "$selectedCompany")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, // Example icon
                        contentDescription = "Trailing Icon"
                    )
                }

                // DropdownMenu to display the list of shipping companies
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false } // Close dropdown when dismissed
                ) {
                    shippingCompanies.forEach { company ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCompany = company.name  // Set the selected company
                                selectedCompanyId = company.id
                                expanded = false  // Close the dropdown
                            }
                        ) {
                            Text(text = company.name)  // Display company name
                        }
                    }
                }
            }
        }


        val shipscreenctx =  LocalContext.current
        Spacer(modifier = Modifier.height(16.dp))  // Add space before the button

        // Display order information
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Order ID: $orderId", style = MaterialTheme.typography.h6)
            Text(text = "Barcode No: $barcodeNo", style = MaterialTheme.typography.h6)
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Save button
        Button(
            onClick = {

                // Action to be performed when the Save button is clicked
                // For example, navigate or save the selectedCompany to preferences
                println("Selected shipping company: $selectedCompany")
                Log.d("ToShipScreen", "Selected shipping company: $selectedCompany")
//                showdialogCamerScreen =false
//                navController.popBackStack()
//                navController.popBackStack()

                if (accessToken != null) {
                    if (orderId != null) {
                        if (barcodeNo != null) {

                            if(ToShipScreenSaveButtonText === "Save"){
                                updatePacked(navController, accessToken,
                                    storeId.toString(), orderId, selectedCompanyId, barcodeNo, scope, scaffoldState, shipscreenctx)

                            } else{
                                navController.clearBackStack(Screen.ToShipScreen.route)
                                navController.clearBackStack(Screen.BarcodeCamerScreen.route)
                                navController.popBackStack()
                                navController.popBackStack()
                                ToShipScreenSaveButtonText = "Save"
                            }


//                            scope.launch {
//                                scaffoldState.snackbarHostState.showSnackbar(
//                                    message = "orderId ${orderId} packed updated",
//                                    actionLabel = "OK",
//                                    duration = SnackbarDuration.Short
//                                )
//                            }

                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()  // Make the button fill the width
        ) {
            Text(text = ToShipScreenSaveButtonText)  // Text inside the button
        }
    }
}


fun updatePacked(
    navController: NavController,
    accessToken: String,
    storeId: String,
    orderId: String,
    courier: Int,
    trackingNo: String,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    shipscreenctx: Context
) {
    try {
        RetrofitInstance.api.orderPackedUpdate(accessToken, storeId, orderId, courier,trackingNo).enqueue(object :
            Callback<OrderResponse> {

            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.isSuccessful) {

//                    scope.launch {
//                        scaffoldState.snackbarHostState.showSnackbar(
//                            message = "orderId ${orderId} packed updated",
//                            actionLabel = "OK",
//                            duration = SnackbarDuration.Short
//                        )
//                    }
                    Toast.makeText(shipscreenctx, "orderId ${orderId} packed updated", Toast.LENGTH_LONG).show();
                    showdialogCamerScreen =false
                    ToShipScreenSaveButtonText= "Go Back"
                    navController.clearBackStack(Screen.BarcodeCamerScreen.route)
                    navController.clearBackStack(Screen.ToShipScreen.route)

                    navController.popBackStack()
                    navController.popBackStack()

                } else {
                    navController.navigate(Screen.SignUp.route)
                    Log.e("Orders", "Failed to fetch dashboard: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                navController.navigate(Screen.SignUp.route)
                Log.e("Orders", "Network error: ${t.message}")
            }
        })
    } catch (e: Exception) {
        Log.e("Orders", "Error fetching orders: ${e.message}")
    }
}
var ToShipScreenSaveButtonText by mutableStateOf("Save")
@Preview(showBackground = true)
@Composable
fun PreviewToShipScreen() {
    ToShipScreen(NavController(LocalContext.current))
}



