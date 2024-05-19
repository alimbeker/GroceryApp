package arbuz.android.groceryapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.data.database.GroceryDatabase
import arbuz.android.groceryapp.data.repository.GroceryRepository
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GroceryRepository

    val groceries: LiveData<List<Grocery>>

    init {
        val groceryDao = GroceryDatabase.getDatabase(application).groceryDao()
        repository = GroceryRepository(groceryDao)
        groceries = repository.allGroceries.asLiveData()
    }


    private val groceryList = listOf(
        Grocery(name = "Beans", price = 1.0, imageUrl = "file:///android_asset/beans.png"),
        Grocery(name = "Banana", price = 0.5, imageUrl = "file:///android_asset/banana.jpeg"),
        Grocery(name = "Carrot", price = 0.7, imageUrl = "file:///android_asset/carrot.png"),
        Grocery(name = "Peppers", price = 0.3, imageUrl = "file:///android_asset/peppers.png"),
        Grocery(name = "Green Salad", price = 0.4, imageUrl = "file:///android_asset/green_salad.jpeg"),
        Grocery(name = "Potatoes", price = 0.8, imageUrl = "file:///android_asset/potatoes.png"),
        Grocery(name = "Onions", price = 0.2, imageUrl = "file:///android_asset/onions.png"),

        )

    fun loadGroceries() {
        if (groceries.value != groceryList) {
            viewModelScope.launch {
                repository.deleteAll()
                repository.insertAll(groceryList)
            }
        }
    }

    fun addToCart(grocery: Grocery) {
        viewModelScope.launch {
            grocery.quantityInCart++
            repository.updateQuantityInCart(grocery.id, grocery.quantityInCart)
        }
    }

    fun removeFromCart(grocery: Grocery) {
        viewModelScope.launch {
            if (grocery.quantityInCart > 0) {
                grocery.quantityInCart--
                repository.updateQuantityInCart(grocery.id, grocery.quantityInCart)
            }
        }
    }
}
