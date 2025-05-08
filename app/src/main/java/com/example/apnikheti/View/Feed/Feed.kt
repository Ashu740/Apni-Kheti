package com.example.apnikheti.View.Feed

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.apnikheti.features.marketData.domain.presentation.MandiScreen
import com.example.apnikheti.viewModel.mandiViewModel.MandiViewModel

@Composable
fun Feed(navController: NavController, viewModel: MandiViewModel) {
    MandiScreen(viewModel = viewModel)
}