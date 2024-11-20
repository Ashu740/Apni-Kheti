package com.example.apnikheti.Location

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel
import android.Manifest
import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.apnikheti.MainActivity
import com.example.apnikheti.R
import java.util.Timer
import kotlin.concurrent.schedule

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocationDisplay(
    locationViewModel: LocationViewModel,
    locationUtil: LocationUtil,
    context: Context,
) {
    val location = locationViewModel.location.value
    val checkPermission = remember {
        mutableStateOf(locationViewModel.checkPermission.value)
    }
    val address = location?.let {
        locationUtil.reverseGeocodeLocation(location)
    }

     val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if(permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                //have permission
                checkPermission.value = true
                locationUtil.requestLocationUpdates(viewModel = locationViewModel)
            } else {
                checkPermission.value = false
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                if(rationalRequired) {
                    //show dialog
                    Toast.makeText(context, "Location permission required", Toast.LENGTH_SHORT).show()
                } else {
                    //open settings
                    Toast.makeText(context, "Go to android setting and enable Location Permission", Toast.LENGTH_SHORT).show()
                }
            }
        })
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (locationUtil.hasLocationPermission(context)) {
            //permission already granted update the location
            checkPermission.value = true
            locationUtil.requestLocationUpdates(viewModel = locationViewModel)
        }
        if (location != null) {
            val addArray: List<String> = address?.split(",") ?: emptyList()
            val len = addArray.size
            val state: List<String> = addArray[len-2].split(" ")
            val stateName: String
            if(state.size > 3) {
                stateName = state[state.size-3]+" "+state[state.size-2]
            }else {
                stateName = state[state.size-2]
            }
            if(len>3) {
                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    Text(text = "${addArray[len-3]}, $stateName", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = addArray[len-1], fontSize = 15.sp, fontWeight = FontWeight.Light)
                }
            }else if (len > 2) {
                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    Text(text = "${addArray[len-2]}, $stateName", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = addArray[len-1], fontSize = 15.sp, fontWeight = FontWeight.Light)
                }
            }else {
                Text(text = "$address", fontSize = 15.sp)
            }

        } else {
            Text(text = "Location not available")
        }
        Spacer(modifier = Modifier.height(6.dp))
        IconButton(onClick = {
            if (locationUtil.hasLocationPermission(context)) {
                //permission already granted update the location
                checkPermission.value = true
                locationUtil.requestLocationUpdates(viewModel = locationViewModel)
            }else if(!locationUtil.hasLocationPermission(context)) {
                //ask for permission
                checkPermission.value = false
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }}, modifier = Modifier.fillMaxWidth(0.6f)) {
            if(checkPermission.value) {
                Icon(painter = painterResource(id = R.drawable.baseline_refresh_24), contentDescription = "refresh")
            }else {
                Box(modifier = Modifier) {
                    Text(text = " Allow Location ", textAlign = TextAlign.Start, modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(100)))
                }
            }

        }
    }
}