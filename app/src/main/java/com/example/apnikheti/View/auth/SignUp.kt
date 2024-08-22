package com.example.apnikheti.View.auth

import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
fun SignUp(navController: NavController, authViewModel: AuthViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
        Log.i("Signup page", "signup page")
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            val email = remember {
                mutableStateOf("")
            }

            val authState = authViewModel.authState.observeAsState()
            val context = LocalContext.current

            LaunchedEffect(authState.value) {
                when(authState.value){
                    is AuthState.Authenticated -> navController.navigate("/home"){
                        popUpTo("/signup") {inclusive = true}
                    }
                    is AuthState.Error -> Toast
                        .makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                    else -> Unit
                }
            }


//            val mobileNo = remember {
//                mutableStateOf("")
//            }
            val password = remember {
                mutableStateOf("")
            }
            val passwordVisibility = remember {
                mutableStateOf(false)
            }
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
//            OutlinedTextField(value = mobileNo.value, onValueChange = {
//                mobileNo.value = it
//            },
//                singleLine = true,
//                label = { Text(text = "Mobile No")},
//                placeholder = { Text(text = "Mobile No")},
//                shape = RoundedCornerShape(15),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 16.dp, end = 16.dp)
//            )
//            Spacer(modifier = Modifier.size(5.dp))
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            },
                singleLine = true,
                label = { Text(text = "Password")},
                placeholder = { Text(text = "Password")},
                shape = RoundedCornerShape(15),
                visualTransformation = if(passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (!passwordVisibility.value){
                        R.drawable.baseline_remove_red_eye_24
                    }else{
                        R.drawable.eye_slash_svgrepo_com
                    }

                    val description = if(passwordVisibility.value) "Hide Password" else "Show Password"

                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(painter = painterResource(id = image), contentDescription = description)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Button(onClick = {
                authViewModel.signUpClicked(email.value, password.value) },
                enabled = authState.value != AuthState.Loading,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                Text(text = "SignUp")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = "Already have Account SignUp", modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { navController.popBackStack() })
            Spacer(modifier = Modifier.size(5.dp))
            TextButton(onClick = { authViewModel.handleGoogleSignIn(context, navController)},
                modifier = Modifier.fillMaxWidth(),
                ) {
                Text(text = "Continue With Google", textAlign = TextAlign.Center)
            }
        }
    }
}