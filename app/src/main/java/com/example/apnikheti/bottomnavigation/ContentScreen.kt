package com.example.apnikheti.bottomnavigation

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.apnikheti.Location.LocationUtil
import com.example.apnikheti.View.APMC.APMC
import com.example.apnikheti.View.Feed.Feed
import com.example.apnikheti.View.Shop.Shop
import com.example.apnikheti.View.dashboard.Dashboard
import com.example.apnikheti.features.YourCrop.data.CropData
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel
import com.example.apnikheti.viewModel.mandiViewModel.MandiViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedItem: Int,
    navController: NavController,
    authViewModel: AuthViewModel,
    locationViewModel: LocationViewModel,
    weatherState: LocationViewModel.WeatherState,
    context: Context,
    locationUtil: LocationUtil,
    imagePicker: ActivityResultLauncher<PickVisualMediaRequest>,
    uriState: MutableStateFlow<String>,
    mandiViewModel: MandiViewModel,
    crops: CropData? = null,
) {

    when (selectedItem) {
        0 -> Dashboard(
            navController = navController,
            authViewModel = authViewModel,
            locationViewModel = locationViewModel,
            weatherState = weatherState,
            context =  context,
            locationUtil = locationUtil
        )

        1 -> APMC(navController = navController, imagePicker = imagePicker, uriState = uriState)
        2 -> Feed(navController = navController, viewModel = mandiViewModel)
        3 -> Shop(navController = navController, crops = crops)
    }
}
