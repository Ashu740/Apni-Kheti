package com.example.apnikheti.features.marketData.data.remote

import com.squareup.moshi.Json

data class MarketDto(
    @field:Json(name = "records")
    val records: List<MarketDataDto>,
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "limit")
    val limit: String,
    @field:Json(name = "offset")
    val offset: String
)

