package arbuz.android.groceryapp.presentation.listener

import arbuz.android.groceryapp.data.database.Grocery

sealed class GroceryViewEvent {
    data class AddToCart(val grocery: Grocery) : GroceryViewEvent()
    data class RemoveFromCart(val grocery: Grocery) : GroceryViewEvent()
    data class ResetToZero(val grocery: Grocery) : GroceryViewEvent()
}

interface GroceryViewContract {
    fun send(event: GroceryViewEvent)
}