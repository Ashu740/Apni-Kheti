package com.example.apnikheti.features.data

import CropDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apnikheti.features.data.model.CropSave

//@Database(entities = [CropSave::class], version = 1, exportSchema = false)
//abstract class CropDatabase : RoomDatabase() {
//
//    abstract fun cropDao(): CropDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: CropDatabase? = null
//
//        fun getDatabase(context: Context): CropDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext.toString(),
//                    CropDatabase::class.java,
//                    "crop_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
