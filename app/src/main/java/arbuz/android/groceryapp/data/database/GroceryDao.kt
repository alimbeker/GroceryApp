package arbuz.android.groceryapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import arbuz.android.groceryapp.data.database.Grocery
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groceries: List<Grocery>)

    @Query("SELECT * FROM grocery_table")
    fun getAllGroceries(): Flow<List<Grocery>>
}