package com.example.apnikheti.navigation

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.apnikheti.HomeScreen
import com.example.apnikheti.Loading
import com.example.apnikheti.View.auth.LogIn
import com.example.apnikheti.View.auth.SignUp
import com.example.apnikheti.View.auth.StartView
import com.example.apnikheti.features.YourCrop.data.CropData
import com.example.apnikheti.features.YourCrop.mapper.loadCropsFromAssets
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel
import com.example.apnikheti.viewModel.mandiViewModel.MandiViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun NavHostScreen(
    authViewModel: AuthViewModel,
    locationViewModel: LocationViewModel,
    weatherState: LocationViewModel.WeatherState,
    imagePicker: ActivityResultLauncher<PickVisualMediaRequest>,
    uriState: MutableStateFlow<String>,
    mandiViewModel: MandiViewModel,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.AuthNav.route) {
        navigation(
            startDestination = ScreenRoutes.LoadingScreen.route,
            route = ScreenRoutes.AuthNav.route
        ){
            composable(route = ScreenRoutes.LoadingScreen.route){
                Loading(navController = navController, authViewModel = authViewModel)
            }

            composable(route = ScreenRoutes.StartScreen.route){
                StartView(navController = navController, authViewModel = authViewModel)
            }

            composable(route = ScreenRoutes.LoginScreen.route){
                LogIn(navController = navController, authViewModel = authViewModel)
            }

            composable(route = ScreenRoutes.SignUpScreen.route) {
                SignUp(navController = navController, authViewModel = authViewModel)
            }
        }

        composable(ScreenRoutes.HomeNav.route){
            HomeScreen(navController = navController,
                authViewModel = authViewModel,
                locationViewModel = locationViewModel,
                weatherState = weatherState,
                imagePicker = imagePicker,
                uriState = uriState,
                mandiViewModel = mandiViewModel,
//                crops = crops
            )
        }
    }
}