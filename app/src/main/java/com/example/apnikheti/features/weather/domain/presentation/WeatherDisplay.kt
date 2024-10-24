package com.example.apnikheti.features.weather.domain.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.apnikheti.features.weather.domain.weather.WeatherData

@Composable
fun WeatherDisplay(weatherData: WeatherData) {
    Column(modifier = Modifier.padding(3.dp)) {
        Image(painter = painterResource(id = weatherData.weatherType.iconRes), contentDescription = weatherData.weatherType.weatherDesc)
        Text(text = "Temperature: ${weatherData.temperatureCelsius}°C")
        Text(text = "Pressure: ${weatherData.pressure} hPa")
        Text(text = "Wind Speed: ${weatherData.windSpeed} m/s")
        Text(text = "Humidity: ${weatherData.humidity}%")
        Text(text = "Weather Type: ${weatherData.weatherType.weatherDesc}")
    }
}