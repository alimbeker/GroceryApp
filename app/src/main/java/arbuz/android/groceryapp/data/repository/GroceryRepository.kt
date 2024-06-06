package arbuz.android.groceryapp.data.repository

import android.util.Log
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.data.database.GroceryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroceryRepository @Inject constructor(private val groceryDao: GroceryDao) {

    val allGroceries: Flow<List<Grocery>> = groceryDao.getAllGroceries()

    suspend fun insertAll(groceries: List<Grocery>) {
        groceryDao.insertAll(groceries)
    }

    suspend fun insert(grocery: Grocery) {
        groceryDao.insert(grocery)
    }

    suspend fun deleteAll() {
        groceryDao.deleteAll()
    }

    suspend fun delete(grocery: Grocery) {
        groceryDao.delete(grocery)
    }

    suspend fun updateQuantityInCart(id: Int, quantityInCart: Int) {
        Log.d("GroceryRepository", "Updating quantity for id $id to $quantityInCart")
        groceryDao.updateQuantityInCart(id, quantityInCart)
    }
}