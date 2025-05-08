package com.example.apnikheti.View.Shop

import CropScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

import com.example.apnikheti.features.YourCrop.data.CropData

@Composable
fun Shop(navController: NavController, crops: CropData?) {



//   crops?.let {
//      // Display your list of crops here
//      CropScreen(cropData = it)
//   }

   crops?.let { CropScreen(cropData = it) }
}
