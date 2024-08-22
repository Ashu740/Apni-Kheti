package com.example.apnikheti

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apnikheti.View.APMC.APMC
import com.example.apnikheti.View.Feed.Feed
import com.example.apnikheti.View.Shop.Shop
import com.example.apnikheti.View.auth.LogIn
import com.example.apnikheti.View.auth.SignUp
import com.example.apnikheti.View.dashboard.Dashboard
import com.example.apnikheti.bottomnavigation.ContentScreen
import com.example.apnikheti.bottomnavigation.HomeScreen
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun NavHostScreen(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/loading") {
        composable("/loading") {
            Loading(navController = navController, authViewModel = authViewModel)
        }
        composable("/login") {
            LogIn(navController = navController, authViewModel = authViewModel)
        }
        composable("/signup") {
            SignUp(navController = navController, authViewModel = authViewModel)
        }
        composable("/home") {
            HomeScreen(navController = navController)
        }
//        composable("/dashboard") {
//            Dashboard(navController = navController, authViewModel = authViewModel)
//        }
//        composable("/apmc") {
//            APMC(navController = navController)
//        }
//        composable("/feed") {
//            Feed(navController = navController)
//        }
//        composable("/shop") {
//            Shop(navController = navController)
//        }

    }
}