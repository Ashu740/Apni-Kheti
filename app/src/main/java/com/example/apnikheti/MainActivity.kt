package com.example.apnikheti

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.apnikheti.navigation.NavHostScreen
import com.example.apnikheti.ui.theme.ApniKhetiTheme
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : AuthViewModel by viewModels()
        installSplashScreen()
            .apply {
                setKeepOnScreenCondition {
                    authViewModel.isLoading.value == true
                }
            }
        setContent {
            ApniKhetiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHostScreen(authViewModel)
                }
            }
        }
    }
}

