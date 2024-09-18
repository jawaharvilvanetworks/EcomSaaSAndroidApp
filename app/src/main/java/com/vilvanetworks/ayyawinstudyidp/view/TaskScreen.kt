package com.vilvanetworks.ayyawinstudyidp.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.vilvanetworks.ayyawinstudyidp.ui.theme.black
import com.vilvanetworks.ayyawinstudyidp.ui.theme.ghost_white


import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.vilvanetworks.ayyawinstudyidp.MainActivity
import com.vilvanetworks.ayyawinstudyidp.objectInterface.*
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
@Composable
fun TaskScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ghost_white),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        val accessToken = PrefHelper().getStringFromShared(SharedPrefKey.accessToken)
        val storeId = PrefHelper().getIntFromShared(SharedPrefKey.storeid)


        val king = OrdersViewModel()
        if (accessToken != null) {
            king.fetchOrders(accessToken, storeId.toString())
            OrdersScreen(king)
        }



    }


}
@Composable
fun OrderTable(orderResponse: OrderResponse) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Table Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TableCell(content = "Order ID", isHeader = true, modifier = Modifier.weight(1f))
            TableCell(content = "Name", isHeader = true, modifier = Modifier.weight(1f))
            TableCell(content = "Amount", isHeader = true, modifier = Modifier.weight(1f))
            TableCell(content = "Status", isHeader = true, modifier = Modifier.weight(1f))
        }

        // Table Rows
        orderResponse.data.forEach { order ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCellButton(content = order.order_id, modifier = Modifier.weight(1f))
                TableCell(content = order.name, modifier = Modifier.weight(1f))
                TableCell(content = "₹${order.amt}", modifier = Modifier.weight(1f))
                TableCell(content = order.payment_status, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun TableCell(content: String, isHeader: Boolean = false, modifier: Modifier = Modifier) {
    Text(
        text = content,
        modifier = modifier.padding(8.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
        )
    )
}

@Composable
fun TableCellButton(content: String, isHeader: Boolean = false, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Handle button click */ }, // You can provide action here
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = content,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}






@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    TaskScreen(NavController(LocalContext.current))
}


@Composable
fun OrdersScreen(viewModel: OrdersViewModel) {
    // Using state to trigger recomposition when the data changes
    val orderResponse by viewModel.orderResponseState.collectAsState()

    // UI part
    Column(modifier = Modifier.fillMaxSize()) {
        if (orderResponse != null) {
            // Display data from orderResponse
//            Text("Message: ${orderResponse!!.message}")

            OrderTable(orderResponse = orderResponse!!)
            // You can create more UI elements to display other parts of `orderResponse`
        } else {
            // Show a loading state or error
            Text("Loading...")
        }
    }
}

class OrdersViewModel : ViewModel() {

    // MutableStateFlow to handle dynamic updates
    private val _orderResponseState = MutableStateFlow<OrderResponse?>(null)
    val orderResponseState: StateFlow<OrderResponse?> = _orderResponseState

    // Function to fetch orders from the API
    fun fetchOrders(accessToken: String, storeId: String) {
        try {
            RetrofitInstance.api.getOrders(accessToken, storeId, "3").enqueue(object : Callback<OrderResponse> {

                override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                    if (response.isSuccessful) {
                        val dashboardResponse = response.body()
                        if (dashboardResponse != null) {
                            viewModelScope.launch {
                                // Update the MutableStateFlow, triggering UI update
                                _orderResponseState.value = dashboardResponse
                                Log.d("Orders", "Message: ${dashboardResponse.message}")
                            }
                        } else {
                            Log.e("Orders", "Response body is null")
                        }
                    } else {
                        Log.e("Orders", "Failed to fetch dashboard: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    Log.e("Orders", "Network error: ${t.message}")
                }
            })
        } catch (e: Exception) {
            Log.e("Orders", "Error fetching orders: ${e.message}")
        }
    }
}

