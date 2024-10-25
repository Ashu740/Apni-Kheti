package com.example.apnikheti.features.weather.domain.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.model.content.CircleShape
import com.example.apnikheti.features.weather.domain.weather.WeatherData

@Composable
fun WeatherDisplay(weatherData: WeatherData) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.2f), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically){
                Text(text = "${weatherData.temperatureCelsius}", fontSize = 35.sp, fontWeight = FontWeight.Bold)
                Text(text = "°C", fontSize = 20.sp)
                Column(modifier = Modifier.padding(start = 8.dp), horizontalAlignment = Alignment.Start) {
                    Text(text = "H: ${weatherData.highWeather}°C", fontWeight = FontWeight.ExtraLight)
                    Text(text = "L: ${weatherData.lowWeather}°C", fontWeight = FontWeight.ExtraLight)

                }
            }
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = weatherData.weatherType.weatherDesc,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(80.dp)
                )

                Text(text = weatherData.weatherType.weatherDesc, fontSize = 20.sp)
                Spacer(modifier = Modifier.size(16.dp))
            }
            
        }
        HorizontalDivider()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .fillMaxSize(0.2f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Humidity", fontWeight = FontWeight.Light)
                Text(text = "${weatherData.humidity}%", fontWeight = FontWeight.SemiBold)
            }
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Pressure", fontWeight = FontWeight.Light)
                Text(text = "${weatherData.pressure} hPa", fontWeight = FontWeight.SemiBold)
            }
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Wind Speed", fontWeight = FontWeight.Light)
                Text(text = "${weatherData.windSpeed} m/s", fontWeight = FontWeight.SemiBold)
            }
        }

    }
}