package com.example.apnikheti.viewModel.mandiViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apnikheti.features.marketData.data.remote.MarketDataDto
import com.example.apnikheti.features.marketData.data.remote.marketApi
import kotlinx.coroutines.launch

class MandiViewModel: ViewModel() {
    private val _state = mutableStateOf(MarketData())
    val state: State<MarketData> = _state

    init {
        fetchMandi()
    }

    private fun fetchMandi() {
        viewModelScope.launch {
            try{
                val response = marketApi.getMarketData(
//                    commodity = "Red Chilli",
//                    state = "Uttar Pradesh",
//                    district = "Ghaziabad",
                )

                _state.value = _state.value.copy(
                    records = response.records, // Update with the list of records
                    isLoading = false
                )
                println("Fetched data: ${_state.value}")
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message.toString(),
                )
            }
        }
    }
    data class MarketData(
        val records: List<MarketDataDto> = emptyList(),
        val isLoading: Boolean = true,
        val error: String? = null
    )

}