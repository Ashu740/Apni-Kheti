package com.example.apnikheti.features.weather.domain.repository

import com.example.apnikheti.features.weather.domain.util.Resource
import com.example.apnikheti.features.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}