package com.example.apnikheti.viewModel.selectedCrop.Repository

import CropDao
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//class CropRepository(private val cropDao: CropDao) {
//
//    suspend fun insertCrops(crops: List<CropEntity>) {
//        withContext(Dispatchers.IO) {
//            cropDao.insertAll(crops)
//        }
//    }
//
//    suspend fun getCrops(): List<CropEntity> {
//        return withContext(Dispatchers.IO) {
//            cropDao.getAllCrops()
//        }
//    }
//
//    suspend fun clearCrops() {
//        withContext(Dispatchers.IO) {
//            cropDao.deleteAllCrops()
//        }
//    }
//}