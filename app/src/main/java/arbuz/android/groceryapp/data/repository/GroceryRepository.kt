package arbuz.android.groceryapp.data.repository

import android.util.Log
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.data.database.GroceryDao
import kotlinx.coroutines.flow.Flow

class GroceryRepository(private val groceryDao: GroceryDao) {

    val allGroceries: Flow<List<Grocery>> = groceryDao.getAllGroceries()

    suspend fun insertAll(groceries: List<Grocery>) {
        groceryDao.insertAll(groceries)
    }

    suspend fun deleteAll() {
        groceryDao.deleteAll()
    }

    suspend fun updateQuantityInCart(id: Int, quantityInCart: Int) {
        Log.d("GroceryRepository", "Updating quantity for id $id to $quantityInCart")
        groceryDao.updateQuantityInCart(id, quantityInCart)
    }
}