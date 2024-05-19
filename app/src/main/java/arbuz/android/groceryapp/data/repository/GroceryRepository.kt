package arbuz.android.groceryapp.data.repository

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
        groceryDao.updateQuantityInCart(id, quantityInCart)
    }
}