package com.example.apnikheti.View.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.apnikheti.R
import com.example.apnikheti.navigation.graph.ScreenRoutes
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel

@Composable
fun StartView(navController: NavController, authViewModel: AuthViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .absoluteOffset(x = (55).dp, y = (-50).dp)) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anilmation1))
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                )
            }
                Text(text = "BOOST", fontSize = 30.sp, modifier = Modifier.padding(start = 16.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    Text(text = "\t\t\tAGRICULTURE\t\t\t", fontSize = 35.sp, modifier = Modifier
                        .padding(end = 16.dp)
                        .border(2.dp, color = colorResource(id = R.color.midBlack), shape = RoundedCornerShape(50))
                    )
                }
                Text(text = "PRODUCTION", fontSize = 30.sp, modifier = Modifier.padding(start = 16.dp))
                Text(text = "THROUGH", fontSize = 30.sp, modifier = Modifier.padding(start = 16.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    Text(text = "\t\t\tAPNI KHETI\t\t\t", fontSize = 35.sp, modifier = Modifier
                    .padding(end = 16.dp)
                    .border(2.dp, color = colorResource(id = R.color.midBlack), shape = RoundedCornerShape(50))
                )
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(70))
                .background(color = colorResource(id = R.color.green)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                Button(modifier = Modifier
                    .padding(start = 10.dp)
                    .height(100.dp),
                    onClick = { navController.navigate(ScreenRoutes.LoginScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(id = R.color.midBlack)
                    )
                ) {
                        Text(text = "LOGIN", fontSize = 20.sp )
                }

                Button(modifier = Modifier
                    .padding(20.dp)
                    .height(100.dp),
                    onClick = { navController.navigate(ScreenRoutes.SignUpScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.midBlack),
                    contentColor = colorResource(id = R.color.midWhite)
                ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "SIGNUP", fontSize = 20.sp)
                }
            }
        }
    }
}