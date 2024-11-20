package com.example.apnikheti.View.APMC

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.apnikheti.features.geminichatbot.presentation.ChatScreen
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun APMC(navController: NavController, imagePicker: ActivityResultLauncher<PickVisualMediaRequest>,
         uriState: MutableStateFlow<String>
){
    ChatScreen(imagePicker, uriState)
}