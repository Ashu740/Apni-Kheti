package com.example.apnikheti.viewModel.locationViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apnikheti.features.weather.data.mappers.toWeatherInfo
import com.example.apnikheti.features.weather.data.remote.weatherApi
import com.example.apnikheti.features.weather.domain.weather.WeatherInfo
import com.example.apnikheti.model.LocationData.LocationData
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class LocationViewModel (private val state: SavedStateHandle): ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _weatherState = mutableStateOf(WeatherState())
    val weatherState: State<WeatherState> = _weatherState

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchLocation() {
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeatherData(
                    lat = _location.value?.latitude ?: 0.0,
                    long = _location.value?.longitude ?: 0.0
                )
                _weatherState.value = _weatherState.value.copy(
                    weatherInfo = response.toWeatherInfo(),
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _weatherState.value = _weatherState.value.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = e.message ?: "An unknown error occurred"
                )
            }
        }
    }

    fun updateLocation(newLocationData: LocationData) {
        _location.value = newLocationData
        fetchLocation()
    }
    data class WeatherState(
        val weatherInfo: WeatherInfo? = null,
        val isLoading: Boolean = true,
        val error: String? = null
    )

}

