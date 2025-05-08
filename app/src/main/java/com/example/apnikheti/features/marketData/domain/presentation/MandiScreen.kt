package com.example.apnikheti.features.marketData.domain.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.apnikheti.viewModel.mandiViewModel.MandiViewModel


@Composable
fun MandiScreen(viewModel: MandiViewModel) {
    val state by viewModel.state

    if (state.isLoading) {
        CircularProgressIndicator() // Loader
    } else if (state.error != null) {
        Text(text = "Error: ${state.error}")
    } else {
        LazyColumn {
            items(state.records) { record -> // Directly using `state.records`
                // Display each record, update according to the fields you want to show
                Text(
                    text = "State: ${record.state}, District: ${record.district}, " +
                            "Market: ${record.market}, Commodity: ${record.commodity}, " +
                            "Price: Min: ${record.minPrice}, Max: ${record.maxPrice}, Modal: ${record.modalPrice}"
                )
            }
        }
    }
}

