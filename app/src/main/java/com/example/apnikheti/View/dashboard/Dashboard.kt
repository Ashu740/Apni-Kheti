package com.example.apnikheti.View.dashboard

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImage
import com.example.apnikheti.Location.LocationDisplay
import com.example.apnikheti.Location.LocationUtil
import com.example.apnikheti.features.weather.domain.presentation.WeatherDisplay
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dashboard(navController: NavController, authViewModel: AuthViewModel, locationViewModel: LocationViewModel, weatherState: LocationViewModel.WeatherState) {
    val context  = LocalContext.current
    val user = authViewModel.user.observeAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(text = user.value?.name.toString())
        },
    ) {
        val authState = authViewModel.authState.observeAsState()

        LaunchedEffect(authState.value) {
            when (authState.value) {
                is AuthState.Unauthenticated -> {
                    navController.navigate(ScreenRoutes.StartScreen.route) {
                        popUpTo(ScreenRoutes.HomeNav.route) {
                            inclusive = true
                        }

                    }
                }

                else -> Unit
            }
        }
        val locationUtil = LocationUtil(context = context)
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LocationDisplay(
                    locationViewModel = locationViewModel,
                    locationUtil = locationUtil,
                    context = context
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    when {
                        weatherState.isLoading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }

                        weatherState.error != null -> {
                            Text(text = "Error occurred")
                        }

                        else -> {
                            weatherState.weatherInfo?.currentWeatherData?.let { it1 ->
                                WeatherDisplay(weatherData = it1)

                            }
                        }
                    }
                }
            }
            Button(onClick = { authViewModel.signOutClicked() }) {
                Text(text = "LogOut")
            }
            AsyncImage(model = user.value?.photoUrl, contentDescription = "")
        }
    }
}