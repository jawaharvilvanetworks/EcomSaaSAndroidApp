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
import android.os.StrictMode
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.vilvanetworks.ayyawinstudyidp.MainActivity
import com.vilvanetworks.ayyawinstudyidp.R
import com.vilvanetworks.ayyawinstudyidp.navigation.Screen
import com.vilvanetworks.ayyawinstudyidp.objectInterface.ErrorResponseAddressDetails
import com.vilvanetworks.ayyawinstudyidp.objectInterface.SuccessResponseAddressDetails
import com.vilvanetworks.ayyawinstudyidp.ui.theme.*
import com.vilvanetworks.ayyawinstudyidp.utils.PrefHelper
import com.vilvanetworks.ayyawinstudyidp.utils.RetrofitInstance
import com.vilvanetworks.ayyawinstudyidp.utils.SharedPrefKey
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.regex.Pattern


@SuppressLint("SuspiciousIndentation", "LongLogTag")
@Composable
fun AddressDetails(navController: NavController, mobile: String?) {
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
                HeaderAddressDetails()
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
                                    append("Registration ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        dark_gray,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("Form.")
                                }

                            },
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                    }


                    var name by remember { mutableStateOf("") }
                    var mobilenumber by remember { mutableStateOf(mobile.toString()) }
                    var email by remember { mutableStateOf("") }
                    var type by remember { mutableStateOf("") }
                    var address1 by remember { mutableStateOf("") }
                    var address2 by remember { mutableStateOf("") }
                    var city by remember { mutableStateOf("") }
                    var district by remember { mutableStateOf("") }
                    var state by remember { mutableStateOf("") }
                    var postalcode by remember { mutableStateOf("") }
                    var schoolcollege by remember { mutableStateOf("") }
                    var institute by remember { mutableStateOf("") }
                    var pickmultiplephoto by remember { mutableStateOf("") }

                    var nameerror by remember { mutableStateOf("Please enter valid name.") }
                    var mobilenumbererror by remember { mutableStateOf("Please enter valid mobile number.") }
                    var emailerror by remember { mutableStateOf("Please enter valid email.") }
                    var typeerror by remember { mutableStateOf("Please choose valid type.") }
                    var address1error by remember { mutableStateOf("Please enter valid address1.") }
                    var address2error by remember { mutableStateOf("Please enter valid address2.") }
                    var cityerror by remember { mutableStateOf("Please enter valid city.") }
                    var districterror by remember { mutableStateOf("Please enter valid district.") }
                    var stateerror by remember { mutableStateOf("Please enter valid state.") }
                    var postalcodeerror by remember { mutableStateOf("Please enter valid postal code.") }
                    var schoolcollegeerror by remember { mutableStateOf("Please enter valid school college.") }
                    var instituteerror by remember { mutableStateOf("Please enter valid institute.") }
                    var pickmultiplephotoerror by remember { mutableStateOf("Please choose image.") }


                    var showhidenameerror by remember { mutableStateOf(false) }
                    var showhidemobilenumbererror by remember { mutableStateOf(false) }
                    var showhideemailerror by remember { mutableStateOf(false) }
                    var showhidetypeerror by remember { mutableStateOf(false) }
                    var showhideaddress1error by remember { mutableStateOf(false) }
                    var showhideaddress2error by remember { mutableStateOf(false) }
                    var showhidecityerror by remember { mutableStateOf(false) }
                    var showhidedistricterror by remember { mutableStateOf(false) }
                    var showhidestateerror by remember { mutableStateOf(false) }
                    var showhidepostalcodeerror by remember { mutableStateOf(false) }
                    var showhideschoolcollegeerror by remember { mutableStateOf(false) }
                    var showhideinstituteerror by remember { mutableStateOf(false) }
                    var showhidepickmultiplephotoerror by remember { mutableStateOf(false) }
                    var showhidespinner by remember { mutableStateOf(false) }
                    val context = LocalContext.current
                    val ctx = LocalContext.current
                    val activity = (LocalContext.current as? Activity)
                    val mainActivity = (LocalContext.current as? MainActivity)
//                    var imageUri: Any? by remember { mutableStateOf(R.drawable.ic_home) }
                     var selectedImageUri: Uri? by remember { mutableStateOf(null) }

                    var mExpanded by remember { mutableStateOf(false) }

                    // Create a list of types
                    val mtypes = listOf("Student", "Volunteer")

                    // Create a string value to store the selected city
                    var mSelectedText by remember { mutableStateOf("") }

                    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

                    // Up Icon when expanded and down icon when collapsed
                    val icon = if (mExpanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Name")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),
                            )


                    }

                    if(showhidenameerror)
                        Text(
                            text = nameerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = mobilenumber,
                            onValueChange = { mobilenumber = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Mobile Number")},
                            enabled = false,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }

                    if(showhidemobilenumbererror)
                        Text(
                            text = mobilenumbererror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Email")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),
                            )


                    }

                    if(showhideemailerror)
                        Text(
                            text = emailerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )



                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = mSelectedText,
                            readOnly = true,
                            onValueChange = { mSelectedText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                }.clickable {  mExpanded = !mExpanded },
                            label = {Text("Type")},
                            trailingIcon = {
                                Icon(icon,"contentDescription",
                                    Modifier.clickable { mExpanded = !mExpanded })
                            },

                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),
                        )



                        // Create a drop-down menu with list of cities,
                        // when clicked, set the Text Field text as the city selected
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                            modifier = Modifier
                                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()}),

                        ) {
                            mtypes.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    mSelectedText = label
                                    mExpanded = false
                                    if(label === "Student")
                                        type = "1";
                                    else if(label === "Volunteer")
                                        type = "2";
                                }) {
                                    Text(text = label)
                                }
                            }
                        }
                    }

                    if(showhidetypeerror)
                        Text(
                            text = typeerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = address1,
                            onValueChange = { address1 = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Address 1")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),
                            )


                    }

                    if(showhideaddress1error)
                        Text(
                            text = address1error,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = address2,
                            onValueChange = { address2 = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Address 2")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }

                    if(showhideaddress2error)
                        Text(
                            text = address2error,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )


                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = city,
                            onValueChange = { city = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("City")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }


                    if(showhidecityerror)
                        Text(
                            text = cityerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = district,
                            onValueChange = { district = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("District")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }


                    if(showhidedistricterror)
                        Text(
                            text = districterror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = state,
                            onValueChange = { state = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("State")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }

                    if(showhidestateerror)
                        Text(
                            text = stateerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = postalcode,
                            onValueChange = { postalcode = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Postal code")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }

                    if(showhidepostalcodeerror)
                        Text(
                            text = postalcodeerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )


                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = schoolcollege,
                            onValueChange = { schoolcollege = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("School/College")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }


                    if(showhideschoolcollegeerror)
                        Text(
                            text = schoolcollegeerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Column(Modifier.padding(5.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        OutlinedTextField(
                            value = institute,
                            onValueChange = { institute = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // This value is used to assign to
                                    // the DropDown the same width
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Institue")},
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White, // does not work
                                unfocusedBorderColor = Color.Black,
                                textColor = Color.Black
                            ),

                            )


                    }


                    if(showhideinstituteerror)
                        Text(
                            text = instituteerror,
                            style = MaterialTheme.typography.subtitle1,
                            color = bgred,
                            modifier = Modifier
                                .padding(
                                    top = 5.dp,
                                    bottom = 0.dp
                                )

                        )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column(Modifier.padding(5.dp)) {


                        val photoPicker = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.PickVisualMedia()
                        ) {
                            if (it != null) {
                                Log.d("PhotoPicker", "Selected URI: $it")
                                selectedImageUri = it
                            } else {
                                Log.d("PhotoPicker", "No media selected")
                            }
                        }


                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            selectedImageUri?.let { uri ->
                                ImageRequest.Builder(LocalContext.current)
                                    .data(uri)
                                    .crossfade(enable = true)
                                    .build()
                            }?.let {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(250.dp)
                                        .clickable {
                                            photoPicker.launch(
                                                PickVisualMediaRequest(
                                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                                )
                                            )
                                        },
                                    model = it,
                                    contentDescription = "Avatar Image",
                                    contentScale = ContentScale.Crop,
                                )
                            }


                            Row {

                                Button(
                                    onClick = {


                                        photoPicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )

                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 5.dp,
                                            bottom = 0.dp
                                        ),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text(
                                        text = "Attach photo",
                                        color = white,
                                        style = MaterialTheme.typography.button,
                                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                    )
                                }
                            }


                            if(showhidepickmultiplephotoerror)
                                Text(
                                    text = pickmultiplephotoerror,
                                    style = MaterialTheme.typography.subtitle1,
                                    color = bgred,
                                    modifier = Modifier
                                        .padding(
                                            top = 5.dp,
                                            bottom = 0.dp
                                        )

                                )
                        }

                    }

                    Spacer(modifier = Modifier.padding(5.dp))
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


//                            navController.navigate(Screen.PaymentScreen.route)
                            var error = false;
                            if(name === "") {
                                showhidenameerror = true;
                                error = true;
                            } else {
                                showhidenameerror = false;
                            }

                            if(mobilenumber === "") {
                                showhidemobilenumbererror = true;
                                error = true;
                            } else {
                                showhidemobilenumbererror = false;
                            }

                            if(email === "") {
                                showhideemailerror = true;
                                error = true;
                            }  else {
                                showhideemailerror = false;
                            }

                            if(isValidEmail(email.toString()) === false){
                                showhideemailerror = true;
                                error = true;
                            }  else {
                                showhideemailerror = false;
                            }

                            if(type === "") {
                                showhidetypeerror = true;
                                error = true;
                            } else {
                                showhidetypeerror = false;
                            }

                            if(address1 === "") {
                                showhideaddress1error = true;
                                error = true;
                            } else {
                                showhideaddress1error = false;
                            }

                            if(address2 === "") {
                                showhideaddress2error = true;
                                error = true;
                            } else {
                                showhideaddress2error = false;
                            }

                            if(city === "") {
                                showhidecityerror = true;
                                error = true;
                            } else {
                                showhidecityerror = false;
                            }

                            if(district === "") {
                                showhidedistricterror = true;
                                error = true;
                            }  else {
                                showhidedistricterror = false;
                            }

                            if(state === "") {
                                showhidestateerror = true;
                                error = true;
                            }   else {
                                showhidestateerror = false;
                            }

                            if(postalcode === "") {
                                showhidepostalcodeerror = true;
                                error = true;
                            }    else {
                                showhidepostalcodeerror = false;
                            }

                            if(schoolcollege === "") {
                                showhideschoolcollegeerror = true;
                                error = true;
                            } else {
                                showhideschoolcollegeerror = false;
                            }

                            if(institute === "") {
                                showhideinstituteerror = true;
                                error = true;
                            }  else {
                                showhideinstituteerror = false;
                            }

                            if(selectedImageUri === null) {
                                showhidepickmultiplephotoerror = true;
                                error = true;
                            }  else {
                                showhidepickmultiplephotoerror = false;
                            }

                            if(error){
                                return@Button;
                            }

                            if(!error)
                            {

                                try{

                                    showhidespinner =true;
//                                Log.d("Images", imageUri.toString())
                                    Log.d("selectedImageUri", selectedImageUri.toString())
                                    Log.d("selectedImageUri", selectedImageUri?.encodedPath.toString())
                                    Log.d("selectedImageUri", selectedImageUri?.path.toString())
                                if (selectedImageUri != null) {
                                    val filePath = getRealPathFromURI(context!!, selectedImageUri!!) ?: ""
                                    Log.d("filePath", filePath.toString())
                                    if (filePath != null || filePath == null) {
//                                        var path = (filePath !== "") ? filePath :
                                        val file = File(filePath);

                                        val policy =
                                            StrictMode.ThreadPolicy.Builder().permitAll().build()

                                        StrictMode.setThreadPolicy(policy)
                                        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                                        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
                                        // Use the 'body' variable for further processing, such as uploading to the server
                                        val requestBodyFile = "id_card_photo".toRequestBody("multipart/form-data".toMediaTypeOrNull())


//
//                                        Log.d("Images", body.toString())
//                                        Log.d("Images", file.absolutePath)
//                                        Log.d("Images", file.name)
//                                        Log.d("Images", file.path)
//                                        Log.d("Images", file.extension)
//                                        Log.d("Images body", body.body.contentType().toString())
//                                        val user = UserDataRegister(type = type,  university = schoolcollege, city = city, district = district,
//                                            state = state, education = "BCA",
//                                            name = name, email = email,  mobile = mobilenumber,
//                                            institute = institute, id_card_photo = body, address1 = address1, address2 = address2
//                                        )

//                                        val user = UserDataRegister(type = "1",  university = "Anna University", city = "Chennai", district = "Chennai",
//                                            state = "TamilNadu", education = "BCA",
//                                            name = "Idp Tech", email = "user1111@idp.com",  mobile = "9789084885",
//                                            institute = "IDP", id_card_photo = body, requestBody = requestBodyFile,  address1 = "asdf", address2 = "asdfghi"
//                                        )



                                        //
//--form 'type="1"' \
//--form 'university="Anna University"' \
//--form 'city="Chennai"' \
//--form 'district="Chennai"' \
//--form 'state="TamilNadu"' \
//--form 'education="BCA"' \
//--form 'name="Idp Tech"' \
//--form 'mobile="8122512122"' \
//--form 'email="user1111@idp.com"' \
//--form 'institute="IDP"' \
//--form 'id_card_photo=@"/Users/gopinathravirajan/Downloads/interface-success-01-2.png"' \
//--form 'address1="asdf"' \
//--form 'address2="sdfsadf"'

//                                        Log.d("user", user.toString())

                                    showhidespinner =true;
                                    pickmultiplephotoerror = ""


////                                    val client = OkHttpClient()
////                                    val mediaType = "text/plain".toMediaType()
//                                    val body123 = MultipartBody.Builder().setType(MultipartBody.FORM)
//                                        .addFormDataPart("type","1")
//                                        .addFormDataPart("university","Anna University")
//                                        .addFormDataPart("city","Chennai")
//                                        .addFormDataPart("district","Chennai")
//                                        .addFormDataPart("state","TamilNadu")
//                                        .addFormDataPart("education","BCA")
//                                        .addFormDataPart("name","Idp Tech")
//                                        .addFormDataPart("mobile","8122512122")
//                                        .addFormDataPart("email","user1111@idp.com")
//                                        .addFormDataPart("institute","IDP")
//                                        .addFormDataPart("id_card_photo",file.name,
//                                            File(filePath).asRequestBody("application/octet-stream".toMediaType()))
//                                        .addFormDataPart("address1","asdf")
//                                        .addFormDataPart("address2","sdfsadf")
//                                        .build()
//                                    val request = Request.Builder()
//                                        .url(AppKeys.API_URL+AppKeys.SIGNUPDETAILSSUBMIT)
//                                        .post(body)
//                                        .build()
//                                    val response = client.newCall(request).execute()
//
//
//                                        Log.d("mresponse:","${response.body!!.string()}")
//
////                                        showhidespinner =false;
//                                        var jsonparsesucc : String? =  null
//                                        var jsonparseerr : String? =  null
//
//                                        if(response.body != null)
//                                            jsonparsesucc = response.body!!.string()
//
//                                        if(response.body != null)
//                                            jsonparseerr = response.body!!.string()
//
//                                        val jsonStringsucc = """${jsonparsesucc}"""
//                                        val jsonStringerr = """${jsonparseerr}"""
//
//                                        val gson = Gson()
//                                        var errorResponseerr: ErrorResponseAddressDetails? = null;
//                                        var errorResponsesucc: SuccessResponseAddressDetails? = null;
//                                        if(jsonStringerr != null)
//                                            errorResponseerr = gson.fromJson(jsonStringerr, ErrorResponseAddressDetails::class.java)
//                                        if(jsonStringsucc != null)
//                                            errorResponsesucc = gson.fromJson(jsonStringsucc, SuccessResponseAddressDetails::class.java)
//
//                                        var successDataFlag = false;
//                                        var errorDataFlag = false;
//                                        // Print the parsed object
//                                        if(errorResponsesucc != null){
//                                            println("Code: ${errorResponsesucc.code}, Message: ${errorResponsesucc.message}")
//                                            Log.d("mresponse:","Code: ${errorResponsesucc.code}, Message: ${errorResponsesucc.message}")
//                                            successDataFlag = true;
//                                        }
//
//                                        if(errorResponseerr != null){
//                                            println("Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}")
//                                            Log.d("mresponse:","Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}," +
//                                                    "Errors: ${errorResponseerr.errors} ")
//
//                                            errorDataFlag = true;
//                                        }
//
//                                        if (errorResponsesucc?.code == 200) {
//                                            showhidespinner = false;
//
//                                            var mUserAuthResp = errorResponsesucc;
//                                            if (mUserAuthResp != null) {
//                                                Log.i("mUserAuthResp.data.payment_status:", mUserAuthResp.data.payment_status.toString())
//                                            }
//                                            if (mUserAuthResp != null) {
//                                                PrefHelper().setStringToShared(SharedPrefKey.accessToken, mUserAuthResp.data.token.toString())
//                                            }
//                                            if (mUserAuthResp != null) {
//                                                PrefHelper().setStringToShared(SharedPrefKey.paymentStatus, mUserAuthResp.data.payment_status.toString())
//                                            }
//                                            if (mUserAuthResp != null) {
//                                                if(mUserAuthResp.data.payment_status == "SUCCESS"){
//                                                    navController.popBackStack()
//                                                    navController.navigate(Screen.HomeScreen.route);
//                                                } else {
//                                                    navController.popBackStack()
//                                                    navController.navigate(Screen.PaymentScreen.route);
//
//                                                }
//                                            }
//
//
//                                            navController.navigate(Screen.PaymentScreen.route)
//                                        } else {
//                                            showhidespinner = false;
//                                            errorResponseerr = gson.fromJson(jsonStringerr, ErrorResponseAddressDetails::class.java)
//                                            pickmultiplephotoerror = errorResponseerr?.errors?.id_card_photo?.toString()
//                                                .toString();
//                                            if(pickmultiplephotoerror == ""){
//                                                pickmultiplephotoerror =
//                                                    errorResponseerr?.message?.toString().toString()
//                                            }
//                                            showhidepickmultiplephotoerror = true;
////                                                    navController.navigate(Screen.PaymentScreen.route)
//                                        }
                                        val type = RequestBody.create("text/plain".toMediaTypeOrNull(), type)
                                        val university = RequestBody.create("text/plain".toMediaTypeOrNull(), schoolcollege)
                                        val city = RequestBody.create("text/plain".toMediaTypeOrNull(), city)
                                        val district = RequestBody.create("text/plain".toMediaTypeOrNull(), district)
                                        val state = RequestBody.create("text/plain".toMediaTypeOrNull(), state)
                                        val education = RequestBody.create("text/plain".toMediaTypeOrNull(), institute)
                                        val name = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
                                        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), email)
                                        val mobile = RequestBody.create("text/plain".toMediaTypeOrNull(), mobilenumber)
                                        val institute = RequestBody.create("text/plain".toMediaTypeOrNull(), institute)
//                                        id_card_photo = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file),
                                        val address1 = RequestBody.create("text/plain".toMediaTypeOrNull(), address1)
                                        val address2 = RequestBody.create("text/plain".toMediaTypeOrNull(), address2)

//                                        val type = RequestBody.create("text/plain".toMediaType(), "1")
//                                        val university = RequestBody.create("text/plain".toMediaType(), "Anna University")
//                                        val city = RequestBody.create("text/plain".toMediaType(), "Chennai")
//                                        val district = RequestBody.create("text/plain".toMediaType(), "Chennai")
//                                        val state = RequestBody.create("text/plain".toMediaType(), "TamilNadu")
//                                        val education = RequestBody.create("text/plain".toMediaType(), "BCA")
//                                        val name = RequestBody.create("text/plain".toMediaType(), "Idp Tech")
//                                        val mobile = RequestBody.create("text/plain".toMediaType(), "9789084885")
//                                        val email = RequestBody.create("text/plain".toMediaType(), "user1111@idp.com")
//                                        val institute = RequestBody.create("text/plain".toMediaType(), "IDP")
//                                        val address1 = RequestBody.create("text/plain".toMediaType(), "asdf")
//                                        val address2 = RequestBody.create("text/plain".toMediaType(), "sdfsadf")

// Create MultipartBody.Part for the file
                                        val idCardPhoto = MultipartBody.Part.createFormData(
                                            "id_card_photo", file.name, file.asRequestBody("application/octet-stream".toMediaType())
                                        )

                                        RetrofitInstance.api.uploadDatasignupdetailssubmit(
                                            type, university, city, district, state, education, name, mobile, email, institute, idCardPhoto, address1, address2
                                        ).enqueue(object : Callback<ResponseBody> {
                                            @SuppressLint("LongLogTag")
                                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                                                showhidespinner =false;
                                                var jsonparsesucc : String? =  null
                                                var jsonparseerr : String? =  null

                                                if(response.body() != null)
                                                    jsonparsesucc = response.body()!!.string()

                                                if(response.errorBody() != null)
                                                    jsonparseerr = response.errorBody()!!.string()

                                                val jsonStringsucc = """${jsonparsesucc}"""
                                                val jsonStringerr = """${jsonparseerr}"""

                                                val gson = Gson()
                                                var errorResponseerr: ErrorResponseAddressDetails? = null;
                                                var errorResponsesucc: SuccessResponseAddressDetails? = null;
                                                if(jsonStringerr != null)
                                                    errorResponseerr = gson.fromJson(jsonStringerr, ErrorResponseAddressDetails::class.java)
                                                if(jsonStringsucc != null)
                                                    errorResponsesucc = gson.fromJson(jsonStringsucc, SuccessResponseAddressDetails::class.java)

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
                                                    Log.d("mresponse:","Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}," +
                                                            "Errors: ${errorResponseerr.errors} ")

                                                    errorDataFlag = true;
                                                }

                                                if (response.code() == 200) {
                                                    showhidespinner = false;

                                                    var mUserAuthResp = errorResponsesucc;
                                                    if (mUserAuthResp != null) {
                                                        Log.i("mUserAuthResp.data.payment_status:", mUserAuthResp.data.payment_status.toString())
                                                    }
                                                    if (mUserAuthResp != null) {
                                                        PrefHelper().setStringToShared(SharedPrefKey.accessToken, mUserAuthResp.data.token.toString())
                                                    }
                                                    if (mUserAuthResp != null) {
                                                        PrefHelper().setStringToShared(SharedPrefKey.paymentStatus, mUserAuthResp.data.payment_status.toString())
                                                    }
                                                    if (mUserAuthResp != null) {
                                                        if(mUserAuthResp.data.payment_status == "SUCCESS"){
                                                            navController.popBackStack()
                                                            navController.navigate(Screen.HomeScreen.route);
                                                        } else {
                                                            navController.popBackStack()
                                                            navController.navigate(Screen.PaymentScreen.route);

                                                        }
                                                    }


                                                    navController.navigate(Screen.PaymentScreen.route)
                                                } else {
                                                    showhidespinner = false;

                                                    pickmultiplephotoerror =
                                                        errorResponseerr?.message?.toString().toString()
                                                    showhidepickmultiplephotoerror = true;
//                                                    navController.navigate(Screen.PaymentScreen.route)
                                                }
                                            }



                                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                                println("Network error: ${t.message}")
                                                showhidespinner = false;
                                            }
                                        })



//                                            .enqueue(object : Callback<ResponseBody> {
//                                            @SuppressLint("LongLogTag")
//                                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//
//                                                showhidespinner =false;
//                                                var jsonparsesucc : String? =  null
//                                                var jsonparseerr : String? =  null
//
//                                                if(response.body() != null)
//                                                    jsonparsesucc = response.body()!!.string()
//
//                                                if(response.errorBody() != null)
//                                                    jsonparseerr = response.errorBody()!!.string()
//
//                                                val jsonStringsucc = """${jsonparsesucc}"""
//                                                val jsonStringerr = """${jsonparseerr}"""
//
//                                                val gson = Gson()
//                                                var errorResponseerr: ErrorResponseAddressDetails? = null;
//                                                var errorResponsesucc: SuccessResponseAddressDetails? = null;
//                                                if(jsonStringerr != null)
//                                                    errorResponseerr = gson.fromJson(jsonStringerr, ErrorResponseAddressDetails::class.java)
//                                                if(jsonStringsucc != null)
//                                                    errorResponsesucc = gson.fromJson(jsonStringsucc, SuccessResponseAddressDetails::class.java)
//
//                                                var successDataFlag = false;
//                                                var errorDataFlag = false;
//                                                // Print the parsed object
//                                                if(errorResponsesucc != null){
//                                                    println("Code: ${errorResponsesucc.code}, Message: ${errorResponsesucc.message}")
//                                                    Log.d("mresponse:","Code: ${errorResponsesucc.code}, Message: ${errorResponsesucc.message}")
//                                                    successDataFlag = true;
//                                                }
//
//                                                if(errorResponseerr != null){
//                                                    println("Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}")
//                                                    Log.d("mresponse:","Code: ${errorResponseerr.code}, Message: ${errorResponseerr.message}," +
//                                                            "Errors: ${errorResponseerr.errors} ")
//
//                                                    errorDataFlag = true;
//                                                }
//
//                                                if (response.code() == 200) {
//                                                    showhidespinner = false;
//
//                                                    var mUserAuthResp = errorResponsesucc;
//                                                    if (mUserAuthResp != null) {
//                                                        Log.i("mUserAuthResp.data.payment_status:", mUserAuthResp.data.payment_status.toString())
//                                                    }
//                                                    if (mUserAuthResp != null) {
//                                                        PrefHelper().setStringToShared(SharedPrefKey.accessToken, mUserAuthResp.data.token.toString())
//                                                    }
//                                                    if (mUserAuthResp != null) {
//                                                        PrefHelper().setStringToShared(SharedPrefKey.paymentStatus, mUserAuthResp.data.payment_status.toString())
//                                                    }
//                                                    if (mUserAuthResp != null) {
//                                                        if(mUserAuthResp.data.payment_status == "SUCCESS"){
//                                                            navController.popBackStack()
//                                                            navController.navigate(Screen.HomeScreen.route);
//                                                        } else {
//                                                            navController.popBackStack()
//                                                            navController.navigate(Screen.PaymentScreen.route);
//
//                                                        }
//                                                    }
//
//
//                                                    navController.navigate(Screen.PaymentScreen.route)
//                                                } else {
//                                                    showhidespinner = false;
//
//                                                    pickmultiplephotoerror = errorResponseerr?.errors?.id_card_photo?.toString()
//                                                        .toString();
//                                                    showhidepickmultiplephotoerror = true;
////                                                    navController.navigate(Screen.PaymentScreen.route)
//                                                }
//                                            }
//
//                                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                                                println("Network error: ${t.message}");
//                                                showhidespinner = false;
//                                            }
//                                        })

                                    } else {
                                        // Handle case where file path is null
                                        Log.e("Upload", "File path is null")
                                    }
                                } else {
                                    // Handle case where selectedImageUri is null
                                    Log.e("Upload", "Selected image URI is null")
                                }


                                } catch (e: Exception) {
                                    // on below line we are handling exception.
                                    e.printStackTrace()
                                    showhidespinner =false;
                                    Log.e("Images", e.printStackTrace().toString())
                                }



                            }else{

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

val EMAIL_ADDRESS: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

private fun isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun getRealPathFromURI(context: Context, uri: Uri): String? {
    when {
        // DocumentProvider
        DocumentsContract.isDocumentUri(context, uri) -> {
            when {
                // ExternalStorageProvider
                isExternalStorageDocument(uri) -> {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":").toTypedArray()
                    val type = split[0]
                    // This is for checking Main Memory
                    return if ("primary".equals(type, ignoreCase = true)) {
                        if (split.size > 1) {
                            Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                        } else {
                            Environment.getExternalStorageDirectory().toString() + "/"
                        }
                        // This is for checking SD Card
                    } else {
                        "storage" + "/" + docId.replace(":", "/")
                    }
                }
                isDownloadsDocument(uri) -> {
                    val fileName = getFilePath(context, uri)
                    if (fileName != null) {
                        return Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName
                    }
                    var id = DocumentsContract.getDocumentId(uri)
                    if (id.startsWith("raw:")) {
                        id = id.replaceFirst("raw:".toRegex(), "")
                        val file = File(id)
                        if (file.exists()) return id
                    }
                    val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                    return getDataColumn(context, contentUri, null, null)
                }
                isMediaDocument(uri) -> {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":").toTypedArray()
                    val type = split[0]
                    var contentUri: Uri? = null
                    when (type) {
                        "image" -> {
                            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        }
                        "video" -> {
                            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                        }
                        "audio" -> {
                            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        }
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(split[1])
                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }
            }
        }
        "content".equals(uri.scheme, ignoreCase = true) -> {
            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(context, uri, null, null)
        }
        "file".equals(uri.scheme, ignoreCase = true) -> {
            return uri.path
        }
    }
    return null
}

fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                  selectionArgs: Array<String>?): String? {
    var cursor: Cursor? = null
    val column = "_data"
    val projection = arrayOf(
        column
    )
    try {
        if (uri == null) return null
        cursor = context.contentResolver.query(uri, projection, selection, selectionArgs,
            null)
        if (cursor != null && cursor.moveToFirst()) {
            val index = cursor.getColumnIndexOrThrow(column)
            return cursor.getString(index)
        }
    } finally {
        cursor?.close()
    }
    return null
}


fun getFilePath(context: Context, uri: Uri?): String? {
    var cursor: Cursor? = null
    val projection = arrayOf(
        MediaStore.MediaColumns.DISPLAY_NAME
    )
    try {
        if (uri == null) return null
        cursor = context.contentResolver.query(uri, projection, null, null,
            null)
        if (cursor != null && cursor.moveToFirst()) {
            val index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
            return cursor.getString(index)
        }
    } finally {
        cursor?.close()
    }
    return null
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is ExternalStorageProvider.
 */
fun isExternalStorageDocument(uri: Uri): Boolean {
    return "com.android.externalstorage.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is DownloadsProvider.
 */
fun isDownloadsDocument(uri: Uri): Boolean {
    return "com.android.providers.downloads.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is MediaProvider.
 */
fun isMediaDocument(uri: Uri): Boolean {
    return "com.android.providers.media.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is Google Photos.
 */
fun isGooglePhotosUri(uri: Uri): Boolean {
    return "com.google.android.apps.photos.content" == uri.authority
}



@Preview
@Composable
fun AddressDetails() = AddressDetails(NavController(LocalContext.current), mobile = null)


@Preview
@Composable
fun HeaderAddressDetails() {
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
