package arbuz.android.groceryapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groceries: List<Grocery>)

    @Query("SELECT * FROM grocery_table")
    fun getAllGroceries(): Flow<List<Grocery>>

    @Query("DELETE FROM grocery_table")
    suspend fun deleteAll()

    @Query("UPDATE grocery_table SET quantityInCart = :quantity WHERE id = :id")
    suspend fun updateQuantityInCart(id: Int, quantity: Int)
}
