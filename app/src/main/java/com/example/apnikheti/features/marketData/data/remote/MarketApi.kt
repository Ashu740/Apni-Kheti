package com.example.apnikheti.features.marketData.data.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://api.data.gov.in/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
val marketApi: MarketApi = retrofit.create(MarketApi::class.java);
interface MarketApi {
    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
    suspend fun getMarketData(
        @Query("api-key") apiKey: String = "579b464db66ec23bdd000001c2d21cbad48440d4441fcf7f88cd122e",
        @Query("format") format: String = "json",
        @Query("filters[commodity]") commodity: String? = null,
        @Query("filters[state.keyword]") state: String? = null,
        @Query("filters[district]") district: String? = null,
    ): MarketDto
}