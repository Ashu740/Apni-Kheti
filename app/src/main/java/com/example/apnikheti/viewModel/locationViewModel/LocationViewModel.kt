package com.example.apnikheti.viewModel.locationViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apnikheti.features.weather.data.repository.WeatherRepositoryImpl
import com.example.apnikheti.features.weather.domain.repository.WeatherRepository
import com.example.apnikheti.features.weather.domain.util.Resource
import com.example.apnikheti.features.weather.domain.weather.WeatherInfo
import com.example.apnikheti.model.LocationData.LocationData
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationViewModel (
//    private val repository: WeatherRepository
): ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _weatherInfo = mutableStateOf(WeatherState())
    val weatherInfo: State<WeatherState> = _weatherInfo

    fun updateLocation(newLocationData: LocationData) {
        _location.value = newLocationData
//        viewModelScope.launch {
//            when (val result =
//                repository.getWeatherData(newLocationData.latitude, newLocationData.longitude)) {
//                is Resource.Success -> {
//                    _weatherInfo.value = _weatherInfo.value.copy(
//                        weatherInfo = result.data,
//                        isLoading = false,
//                        error = null
//                    )
//                }
//                is Resource.Error -> {
//                    _weatherInfo.value = _weatherInfo.value.copy(
//                        weatherInfo = null,
//                        isLoading = false,
//                        error = result.message
//                    )
//                }
//            }?: kotlin.run {
//                _weatherInfo.value = _weatherInfo.value.copy(
//                    isLoading = false,
//                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
//                )
//            }
//        }
    }
    data class WeatherState(
        val weatherInfo: WeatherInfo? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )

}

