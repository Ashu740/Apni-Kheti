import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.apnikheti.features.data.model.CropSave
import kotlinx.coroutines.flow.Flow

@Dao
interface CropDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrop(crop: CropSave)

    @Query("SELECT * FROM crop_table WHERE category = :category")
    fun getCropsByCategory(category: String): Flow<List<CropSave>>

    @Query("DELETE FROM crop_table")
    suspend fun clearAll()
}
