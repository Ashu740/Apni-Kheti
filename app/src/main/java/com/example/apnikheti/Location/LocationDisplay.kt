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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.apnikheti.MainActivity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocationDisplay(
    locationViewModel: LocationViewModel,
    locationUtil: LocationUtil,
    context: Context
) {
    val location = locationViewModel.location.value

    val address = location?.let {
        locationUtil.reverseGeocodeLocation(location)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if(permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                //have permission
                locationUtil.requestLocationUpdates(viewModel = locationViewModel)
            } else {
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
            .padding(3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (locationUtil.hasLocationPermission(context)) {
            //permission already granted update the location
            locationUtil.requestLocationUpdates(viewModel = locationViewModel)
        }
        if (location != null) {
            val addArray: List<String> = address?.split(",") ?: emptyList()
            val state: List<String> = addArray[4].split(" ")
            Text(text = "${addArray[3]}, ${state[1]}")
        } else {
            Text(text = "Location not available")
        }

        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = {
            if (locationUtil.hasLocationPermission(context)) {
                //permission already granted update the location
                locationUtil.requestLocationUpdates(viewModel = locationViewModel)
            } else {
                //ask for permission
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text(text = "Get Location")
        }
    }
}