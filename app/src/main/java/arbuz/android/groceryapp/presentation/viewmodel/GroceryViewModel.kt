package arbuz.android.groceryapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.data.database.GroceryDatabase
import arbuz.android.groceryapp.data.repository.GroceryRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GroceryRepository

    val groceries: LiveData<List<Grocery>>


    init {
        val groceryDao = GroceryDatabase.getDatabase(application).groceryDao()
        repository = GroceryRepository(groceryDao)
        groceries = repository.allGroceries.asLiveData()
        viewModelScope.launch {
            syncGroceries()
        }
    }


    private val groceryList = listOf(
        Grocery(1,name = "Beans", price = 1.0, imageUrl = "file:///android_asset/beans.png"),
        Grocery(2,name = "Banana", price = 0.5, imageUrl = "file:///android_asset/banana.jpeg"),
        Grocery(3,name = "Carrot", price = 0.7, imageUrl = "file:///android_asset/carrot.png"),
        Grocery(4,name = "Peppers", price = 0.3, imageUrl = "file:///android_asset/peppers.png"),
        Grocery(5,name = "Green Salad", price = 0.4, imageUrl = "file:///android_asset/green_salad.jpeg"),
        Grocery(6,name = "Potatoes", price = 0.8, imageUrl = "file:///android_asset/potatoes.png"),
        Grocery(7,name = "Onions", price = 0.2, imageUrl = "file:///android_asset/onions.png"),
        Grocery(8,name = "Mushrooms", price = 0.9, imageUrl = "file:///android_asset/mushrooms.jpg"),
        )


    private fun syncGroceries() {
        viewModelScope.launch {
            val currentGroceries = repository.allGroceries.first()
            insertGroceries(currentGroceries)
            deleteGroceries(currentGroceries)
        }
    }

    private suspend fun insertGroceries(currentGroceries: List<Grocery>) {
        val toAdd =
            groceryList.filter { newGrocery -> currentGroceries.none { it.id == newGrocery.id } }
        toAdd.forEach { grocery ->
            repository.insert(grocery)
        }
    }

    private suspend fun deleteGroceries(currentGroceries: List<Grocery>) {
        val toDelete =
            currentGroceries.filter { existingGrocery -> groceryList.none { it.id == existingGrocery.id } }
        toDelete.forEach { grocery ->
            repository.delete(grocery)
        }
    }


    fun addToCart(grocery: Grocery) {
        viewModelScope.launch {
            val newQuantity = grocery.quantityInCart + 1
            Log.d("GroceryViewModel", "Adding to cart: id=${grocery.id}, newQuantity=$newQuantity")
            repository.updateQuantityInCart(grocery.id, newQuantity)
        }
    }

    fun removeFromCart(grocery: Grocery) {
        viewModelScope.launch {
            if (grocery.quantityInCart > 0) {
                val newQuantity = grocery.quantityInCart - 1
                Log.d(
                    "GroceryViewModel",
                    "Removing from cart: id=${grocery.id}, newQuantity=$newQuantity"
                )
                repository.updateQuantityInCart(grocery.id, newQuantity)
            }
        }
    }

    fun resetToZero(grocery: Grocery) {
        viewModelScope.launch {
            val newQuantity = 0
            repository.updateQuantityInCart(grocery.id, newQuantity)
        }
    }

}
