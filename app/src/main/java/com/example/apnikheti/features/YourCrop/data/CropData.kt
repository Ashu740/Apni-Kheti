package com.example.apnikheti.features.YourCrop.data

//import kotlinx.serialization.Serializable

//@Serializable
data class CropData(
    val horticulture_crops: List<Crop>,
    val field_crops: List<Crop>,
    val oilseed_crops: List<Crop>,
    val selected_crops: List<Crop>
)

