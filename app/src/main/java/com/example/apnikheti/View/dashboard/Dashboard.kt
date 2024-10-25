package com.example.apnikheti.View.dashboard

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImage
import com.example.apnikheti.Location.LocationDisplay
import com.example.apnikheti.Location.LocationUtil
import com.example.apnikheti.R
import com.example.apnikheti.View.Topbar.TopBar
import com.example.apnikheti.features.weather.domain.presentation.WeatherDisplay
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dashboard(navController: NavController,
              authViewModel: AuthViewModel,
              locationViewModel: LocationViewModel,
              weatherState: LocationViewModel.WeatherState,
              context: Context,
              locationUtil: LocationUtil
              ) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val user = authViewModel.user.observeAsState()
    Scaffold(
        topBar = {TopBar(content = {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Hi, ${user.value?.name}")
                AsyncImage(model = user.value?.photoUrl, contentDescription = "profile pic", modifier = Modifier.clip(shape = CircleShape))
            }
        }, scrollBehavior = scrollBehavior)},
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {innerPadding ->
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
        ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
//                Icon(painter = painterResource(id = R.drawable.baseline_location_pin_24), contentDescription = "location")
                LocationDisplay(
                    locationViewModel = locationViewModel,
                    locationUtil = locationUtil,
                    context = context
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                when {
                    weatherState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    weatherState.error != null -> {
                        Text(text = weatherState.error)
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
    }
    }
}