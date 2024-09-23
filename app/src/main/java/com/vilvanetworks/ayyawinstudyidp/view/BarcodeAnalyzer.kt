package de.alexander13oster.easycomposebarcodescanner

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey

class BarcodeAnalyzer(private val context: Context, private val navController: NavController) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    private val _navController = navController

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image
            ?.let { image ->
                scanner.process(
                    InputImage.fromMediaImage(
                        image, imageProxy.imageInfo.rotationDegrees
                    )
                ).addOnSuccessListener { barcode ->
                    barcode?.takeIf { it.isNotEmpty() }
                        ?.mapNotNull { it.rawValue }
                        ?.joinToString(",")
                        ?.let {
                            PrefHelper().getStringFromShared(SharedPrefKey.orderid)
                                ?.let { it1 -> Log.d("camerascreen", it1) }
                            Log.d("camerascreen", it)
                            PrefHelper().setStringToShared(SharedPrefKey.barcodeno, it)
                            _navController.navigate(Screen.ToShipScreen.route)
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
                }.addOnCompleteListener {
                    imageProxy.close()
                }
            }
    }

    fun calltoshipment(){

    }
}