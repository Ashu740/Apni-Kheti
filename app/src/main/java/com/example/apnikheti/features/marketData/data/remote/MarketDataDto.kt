package com.example.apnikheti.features.marketData.data.remote

import com.squareup.moshi.Json

data class MarketDataDto(
    @field:Json(name = "state")
    val state: String,
    @field:Json(name = "district")
    val district: String,
    @field:Json(name = "market")
    val market: String,
    @field:Json(name = "commodity")
    val commodity: String,
    @field:Json(name = "variety")
    val variety: String,
    @field:Json(name = "grade")
    val grade: String,
    @field:Json(name = "arrival_date")
    val arrivalDate: String,
    @field:Json(name = "min_price")
    val minPrice: String,
    @field:Json(name = "max_price")
    val maxPrice: String,
    @field:Json(name = "modal_price")
    val modalPrice: String
)

//"state": "Uttar Pradesh",
//"district": "Pillibhit",
//"market": "Puranpur",
//"commodity": "Tomato",
//"variety": "Local",
//"grade": "Non-FAQ",
//"arrival_date": "06/05/2025",
//"min_price": "650",
//"max_price": "725",
//"modal_price": "685"