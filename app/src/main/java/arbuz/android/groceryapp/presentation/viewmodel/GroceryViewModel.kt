package arbuz.android.groceryapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import arbuz.android.groceryapp.data.database.Grocery
import arbuz.android.groceryapp.data.database.GroceryDatabase
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    private val groceryDao = GroceryDatabase.getDatabase(application).groceryDao()
    val groceries: LiveData<List<Grocery>> = groceryDao.getAllGroceries().asLiveData()

    fun loadGroceries() {
        val groceryList = listOf(
            Grocery(name = "Beans", price = 1.0, imageUrl = "file:///android_asset/beans.jpg"),
            Grocery(name = "Banana", price = 0.5, imageUrl = "file:///android_asset/banana.jpg"),
            Grocery(name = "Carrot", price = 0.7, imageUrl = "file:///android_asset/carrot.jpg")
        )

        viewModelScope.launch {
            groceryDao.insertAll(groceryList)
        }
    }
}
