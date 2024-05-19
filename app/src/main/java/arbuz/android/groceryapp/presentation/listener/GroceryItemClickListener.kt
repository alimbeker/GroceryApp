package arbuz.android.groceryapp.presentation.listener

import arbuz.android.groceryapp.data.database.Grocery

interface GroceryItemClickListener {
    fun onAddToCartClicked(grocery: Grocery)
    fun onRemoveFromCartClicked(grocery: Grocery)
}
