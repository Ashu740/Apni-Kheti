package com.example.apnikheti.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.apnikheti.HomeScreen
import com.example.apnikheti.Loading
import com.example.apnikheti.View.auth.LogIn
import com.example.apnikheti.View.auth.SignUp
import com.example.apnikheti.View.auth.StartView
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun NavHostScreen(authViewModel: AuthViewModel) {
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
            HomeScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}