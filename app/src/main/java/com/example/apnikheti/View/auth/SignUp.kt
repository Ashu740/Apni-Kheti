package com.example.apnikheti.View.auth

import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.apnikheti.R
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController, authViewModel: AuthViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
        Log.i("Signup page", "signup page")
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            val email = remember {
                mutableStateOf("")
            }

            val authState = authViewModel.authState.observeAsState()
            val context = LocalContext.current

            LaunchedEffect(authState.value) {
                when(authState.value){
                    is AuthState.Authenticated -> navController.navigate(ScreenRoutes.HomeNav.route){
                        popUpTo(ScreenRoutes.AuthNav.route) {inclusive = true}
                    }
                    is AuthState.Error -> Toast
                        .makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                    else -> Unit
                }
            }

            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animationlog))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "HELLO", fontSize = 30.sp, modifier = Modifier.padding(16.dp))
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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green),
                    containerColor = colorResource(id = R.color.contGreen),
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedBorderColor = Color.Transparent
                ),
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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green),
                    containerColor = colorResource(id = R.color.contGreen),
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedBorderColor = Color.Transparent
                ),
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
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.midBlack), contentColor = colorResource(
                    id = R.color.midWhite)
                ),
                shape = RoundedCornerShape(12),
                enabled = authState.value != AuthState.Loading,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp).align(Alignment.CenterHorizontally)
                ) {
                Text(text = "SignUp")
            }
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = "Already have Account LogIN", modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { navController.popBackStack() })
            Spacer(modifier = Modifier.size(15.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .clip(shape = RoundedCornerShape(25))
                .background(color = colorResource(id = R.color.green))
                .clickable { authViewModel.handleGoogleSignIn(context, navController) }
            ) {
                Row (modifier = Modifier
                    .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(id = R.drawable.search), contentDescription = "google", modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(text = "Continue With Google")
                }
            }
        }
    }
}