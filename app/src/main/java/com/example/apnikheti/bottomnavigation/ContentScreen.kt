package com.example.apnikheti.bottomnavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apnikheti.View.APMC.APMC
import com.example.apnikheti.View.Feed.Feed
import com.example.apnikheti.View.Shop.Shop
import com.example.apnikheti.View.dashboard.Dashboard
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun ContentScreen(selectedItem: Int, navController: NavController) {

    when (selectedItem) {
        0 -> Dashboard(
            navController = navController,
            authViewModel = AuthViewModel()
        )

        1 -> APMC(navController = navController)
        2 -> Feed(navController = navController)
        3 -> Shop(navController = navController)
    }
}
