package com.example.apnikheti.navigation.graph

sealed class ScreenRoutes(val route: String) {

    data object LoadingScreen : ScreenRoutes("/loading")
    data object LoginScreen : ScreenRoutes("/login")
    data object HomeScreen : ScreenRoutes("/home")
    data object SignUpScreen : ScreenRoutes("/signup")
    data object DashBoard : ScreenRoutes("/dashboard")
    data object APMC : ScreenRoutes("/apmc")
    data object Post : ScreenRoutes("/post")
    data object Shop : ScreenRoutes("/shop")
    data object StartScreen : ScreenRoutes("/startScreen")
    data object AuthNav : ScreenRoutes("/auth_nav_graph")

    data object HomeNav : ScreenRoutes("/home_nav_graph")
}