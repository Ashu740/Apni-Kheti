package com.example.apnikheti.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dashboard(navController: NavController, authViewModel: AuthViewModel){
    val user = authViewModel.user.observeAsState()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Text(text = user.value?.name.toString())
    }) {
        val authState = authViewModel.authState.observeAsState()



        LaunchedEffect(authState.value) {
            when(authState.value){
                is AuthState.Unauthenticated -> {
                    navController.navigate("/login") {
                        popUpTo("/dashboard") {inclusive = true}
                    }
                }
                else -> Unit
            }
        }

        Column {
            Button(onClick = { authViewModel.signOutClicked() }) {
                Text(text = "LogOut")
            }
            AsyncImage(model = user.value?.photoUrl, contentDescription = "")

        }
    }
}