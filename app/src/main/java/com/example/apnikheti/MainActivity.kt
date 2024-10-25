package com.example.apnikheti

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.apnikheti.navigation.NavHostScreen
import com.example.apnikheti.ui.theme.ApniKhetiTheme
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : AuthViewModel by viewModels()
        val locationViewModel : LocationViewModel by viewModels()
        val weatherState by locationViewModel.weatherState
        installSplashScreen()
        setContent {
            ApniKhetiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHostScreen(authViewModel, locationViewModel, weatherState)
                }
            }
        }
    }
}

