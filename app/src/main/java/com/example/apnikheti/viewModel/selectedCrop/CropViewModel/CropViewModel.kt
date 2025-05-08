package com.example.apnikheti.viewModel.selectedCrop.CropViewModel

import CropDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.apnikheti.features.data.model.CropSave
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class CropViewModel(private val cropDao: CropDao) : ViewModel() {

    // Fetch crops from the database by category
    val horticultureCrops: Flow<List<CropSave>> = cropDao.getCropsByCategory("horticulture_crops")
    val fieldCrops: Flow<List<CropSave>> = cropDao.getCropsByCategory("field_crops")
    val oilseedCrops: Flow<List<CropSave>> = cropDao.getCropsByCategory("oilseed_crops")
    val selectedCrops: Flow<List<CropSave>> = cropDao.getCropsByCategory("selected_crops")
}


class CropViewModelFactory(private val cropDao: CropDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CropViewModel::class.java)) {
            return CropViewModel(cropDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
