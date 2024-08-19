package com.example.apnikheti.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apnikheti.R
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun LogIn(navController: NavController, authViewModel: AuthViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
        val email = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val passwordVisible = remember {
            mutableStateOf(false)
        }

        val authState = authViewModel.authState.observeAsState()
        val context = LocalContext.current

        LaunchedEffect(authState.value) {
            when(authState.value){
                is AuthState.Authenticated -> navController.navigate("/dashboard"){
                    popUpTo("/login") {inclusive = true}
                }
                is AuthState.Error ->
                    Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }

        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            OutlinedTextField(value = email.value, onValueChange = {
                email.value = it
            },
                singleLine = true,
                label = { Text(text = "Email")},
                placeholder = { Text(text = "Email")},
                shape = RoundedCornerShape(15),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                )
            Spacer(modifier = Modifier.size(5.dp))
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            },
                label = { Text(text = "Password")},
                placeholder = { Text(text = "Password")},
                singleLine = true,
                shape = RoundedCornerShape(15),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (!passwordVisible.value) {
                        R.drawable.baseline_remove_red_eye_24
                    } else R.drawable.baseline_password_24

                    val description = if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                        Icon(painter = painterResource(id = image), description)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Button(onClick = {
                authViewModel.logInClicked(email.value, password.value)
            },
                enabled = authState.value != AuthState.Loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                ) {
                Text(text = "LogIn")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = "Don't have Account SignUp", modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { navController.navigate("/signup") })
            Spacer(modifier = Modifier.size(5.dp))
            TextButton(onClick = { authViewModel.handleGoogleSignIn(context, navController)},
                modifier = Modifier.fillMaxWidth()
                ) {
                Text(text = "Continue With Google", textAlign = TextAlign.Center)
            }
        }
    }
}