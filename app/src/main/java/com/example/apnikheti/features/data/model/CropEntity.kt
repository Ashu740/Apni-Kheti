package com.example.apnikheti.features.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.apnikheti.features.YourCrop.data.Crop
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

@Entity(tableName = "crop_table")
data class CropSave(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val name: String,
    val image: String
)

class CropConverters {
    @TypeConverter
    fun fromString(value: String): List<Crop> {
        val listType = object : TypeToken<List<Crop>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Crop>): String {
        return Gson().toJson(list)
    }
}
