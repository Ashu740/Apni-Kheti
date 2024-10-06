package com.example.apnikheti

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun Loading(navController: NavController, authViewModel: AuthViewModel){
    Log.i("Loading page", "Loading page")
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                authViewModel.isLoading.value = false
                navController.navigate(ScreenRoutes.HomeNav.route){
                popUpTo(navController.graph.findStartDestination().id) {inclusive = true}
                    launchSingleTop = true
            }
            }
            is AuthState.Unauthenticated -> {
                authViewModel.isLoading.value = false
                navController.navigate(ScreenRoutes.StartScreen.route){
                popUpTo(navController.graph.findStartDestination().id) {inclusive = true}}
            }
            else -> Unit
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        print("Loading page")
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }

}