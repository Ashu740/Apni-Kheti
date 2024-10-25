package com.example.apnikheti.Location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.apnikheti.model.LocationData.LocationData
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale


@SuppressLint("MissingPermission")
class LocationUtil(val context: Context) {
    private val _fusedLocationClient : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fun requestLocationUpdates(viewModel: LocationViewModel) {
        var initialInterval = 100L
        val locationCallback = object : LocationCallback() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationData(it.latitude, it.longitude)
                    viewModel.updateLocation(location)

                    // Update the interval for location requests to one hour (3600000L) after the first update
                    if (initialInterval == 100L) {
                        initialInterval = 3600000L
                        // Recreate location request with updated interval
                        val updatedLocationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, initialInterval).build()
                        _fusedLocationClient.requestLocationUpdates(updatedLocationRequest, this, Looper.getMainLooper())
                    }
                }
            }
        }

        val initialLocationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, initialInterval).build()
        _fusedLocationClient.requestLocationUpdates(initialLocationRequest, locationCallback, Looper.getMainLooper())
    }


    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }
    fun reverseGeocodeLocation(location: LocationData): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val coordinate = LatLng(location.latitude, location.longitude)

        val addresses: MutableList<Address>? = geocoder.getFromLocation(coordinate.latitude,coordinate.longitude,1)
        return if (addresses?.isNotEmpty()==true){
            addresses[0].getAddressLine(0)
        }else{
            return "Unknown Location"
        }
    }
}