package com.vilvanetworks.ayyawinstudyidp

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vilvanetworks.ayyawinstudyidp.navigation.Navigation
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.ui.theme.JetComposeLoginUITheme
import com.vilvanetworks.ayyawinstudyidp.view.PaymentScreen
import com.vilvanetworks.ayyawinstudyidp.view.PaymentScreenpaymentstatus
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import kotlinx.serialization.descriptors.PrimitiveKind

class MainActivity : ComponentActivity(), PaymentStatusListener, EasyPermissions.PermissionCallbacks  {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeLoginUITheme {
                    JetComposeLoginUiMain()
                }

            if (hasPermissions()){

            }else{
                requestPermissions()
            }



        }
    }

    companion object {
        const val PERMISSION_CAMERA_REQUEST_CODE = 1
    }


    override fun onTransactionCancelled() {
        TODO("Not yet implemented")
        PaymentScreenpaymentstatus.value = 2;
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        PaymentScreenpaymentstatus.value = 1;
    }

    public fun paymentsuccess() {
        PaymentScreenpaymentstatus.value = 1;
    }


    private fun hasPermissions() =
        EasyPermissions.hasPermissions(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.INTERNET,
//            Manifest.permission.ACCESS_WIFI_STATE,
//            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CAMERA,

        )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermissions() {
        EasyPermissions.requestPermissions(
            this,
            "Request Permissions",
            PERMISSION_CAMERA_REQUEST_CODE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CAMERA,
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
//        binding.apply {
//            tvMsg.text = getString(R.string.granted_msg)
//            btnRequest.isEnabled = false
//        }
    }

}

fun toast(ctx: Context, textshow : String){
    Toast.makeText(ctx, textshow, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun JetComposeLoginUiMain() {
    JetComposeLoginUITheme {
        Surface(color = MaterialTheme.colors.background) {
            Navigation()
        }
    }
}

