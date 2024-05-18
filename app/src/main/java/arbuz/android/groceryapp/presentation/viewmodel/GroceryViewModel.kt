package arbuz.android.groceryapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arbuz.android.groceryapp.data.database.Grocery

class GroceryViewModel : ViewModel() {

    private val _groceries = MutableLiveData<List<Grocery>>()
    val groceries: LiveData<List<Grocery>> get() = _groceries

    fun loadGroceries() {
        val groceryList = listOf(
            Grocery(name = "Apple", price = 1.0, imageUrl = "apple.jpg"),
            Grocery(name = "Banana", price = 0.5, imageUrl = "banana.jpg"),
            Grocery(name = "Carrot", price = 0.7, imageUrl = "carrot.jpg")
        ).map { grocery ->
            grocery.copy(imageUrl = "file:///android_asset/${grocery.imageUrl}")
        }
        _groceries.value = groceryList
    }
}

