package com.example.apnikheti

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apnikheti.ui.theme.ApniKhetiTheme
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            ApniKhetiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHostScreen(authViewModel)
                }
            }
        }
    }
}

