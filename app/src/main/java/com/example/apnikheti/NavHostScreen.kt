package com.example.apnikheti

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apnikheti.auth.LogIn
import com.example.apnikheti.auth.SignUp
import com.example.apnikheti.dashboard.Dashboard
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun NavHostScreen(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/loading") {
        composable("/loading"){
            Loading(navController = navController, authViewModel = authViewModel)
        }
        composable("/login"){
            LogIn(navController = navController, authViewModel = authViewModel)
        }
        composable("/signup"){
            SignUp(navController = navController, authViewModel = authViewModel)
        }
        composable("/dashboard"){
            Dashboard(navController = navController, authViewModel = authViewModel)
        }
    }
}